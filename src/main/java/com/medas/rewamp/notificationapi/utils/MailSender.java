package com.medas.rewamp.notificationapi.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.vo.MailAttachmentVO;
import com.medas.rewamp.notificationapi.business.vo.MailSetupVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility Class to send mail
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 19, 2020
 *
 */
@Slf4j
@Component
public class MailSender {

	@Value("${app.path.attachments}")
	private String attachPath;

	@Autowired
	private FileUtil fileUtil;

	/**
	 * This method will run in separate thread to send mail
	 * 
	 * @param mailSetup
	 * @param data
	 */
	@Async
	public void sendMail(MailSetupVO mailSetup, NotificationVO data) {
		log.info("Mail Sending in progress..");
		log.info("Data: {}", data);
		log.info("Properties: {}", mailSetup.getAuthProps());

		Properties properties = new Properties();
		populateMailProperties(properties, mailSetup.getAuthProps());

		try {
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailSetup.getAuthMail(), mailSetup.getAuthPassword());
				}
			});

			Message msg = new MimeMessage(session);
			msg.setSubject(data.getNotificationSubject());
			msg.setFrom(new InternetAddress(mailSetup.getAuthMail()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.getNotificationId()));

			MimeMultipart multipart = new MimeMultipart("mixed");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(data.getNotificationTemplate(), MediaType.TEXT_HTML.toString());
			multipart.addBodyPart(messageBodyPart);

			populateAttachmentData(multipart, data.getAttachments());

			msg.setContent(multipart);

			Transport transport = session.getTransport();
			transport.connect();
			Transport.send(msg);
			log.info("Successfully Send");
		} catch (Exception ex) {
			log.error("Error on Sending E-mail: ", ex);
		}
	}

	/**
	 * Setting mail properties
	 * 
	 * @param properties
	 * @param jsonElement
	 */
	private void populateMailProperties(Properties properties, JsonElement jsonElement) {
		JsonObject jsonObj = jsonElement.getAsJsonObject();
		for (Entry<String, JsonElement> entry : jsonObj.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().getAsString());
		}
	}

	/**
	 * Populating attachment data to mail
	 * 
	 * @param multipart
	 * @param attachments
	 * @throws MessagingException
	 */
	private void populateAttachmentData(MimeMultipart multipart, List<MailAttachmentVO> attachments)
			throws MessagingException {
		if (attachments != null) {
			MimeBodyPart bodyPart = null;
			for (MailAttachmentVO attachment : attachments) {
				bodyPart = new MimeBodyPart();
				String fileName = getFile(attachment.getAttachment(), attachment);
				// if error on saving file, skipping attachment
				if (fileName == null) {
					continue;
				}
				DataSource source = new FileDataSource(attachPath + fileName);
				bodyPart.setDataHandler(new DataHandler(source));
				if (CommonConstants.INLINE.equals(attachment.getAttachmentType())) {
					bodyPart.setHeader("Content-ID", "<" + attachment.getAttachmentName() + ">");
				}
				bodyPart.setFileName(fileName);
				multipart.addBodyPart(bodyPart);
			}
		}
	}

	/**
	 * Method to save base64 content to file
	 * 
	 * @param fileData
	 * @param attachment
	 * @return String FilePath
	 */
	private String getFile(String fileData, MailAttachmentVO attachment) {
		String fileName = DateUtil.formatDate("8", new Date()) + CommonConstants.UNDERSCORE
				+ attachment.getAttachmentName() + CommonConstants.DOT + attachment.getFileExtension();
		try {
			fileUtil.saveBase64ToFile(fileData, attachPath + fileName);
		} catch (IOException | IllegalArgumentException e) {
			log.error("Error on saving file: [{}] path: [{}] error: [{}]", attachment.getAttachmentName(), fileName,
					e.getMessage());
			return null;
		}
		return fileName;
	}
}
