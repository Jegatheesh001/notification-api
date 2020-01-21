package com.medas.rewamp.notificationapi.utils;

import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
			msg.setContent(multipart);

			Transport transport = session.getTransport();
			transport.connect();
			Transport.send(msg);
			log.info("Successfully Send");
		} catch (Exception ex) {
			log.error("Error on Sending E-mail: ", ex);
		}
	}

	private void populateMailProperties(Properties properties, JsonElement jsonElement) {
		JsonObject jsonObj = jsonElement.getAsJsonObject();
		for (Entry<String, JsonElement> entry : jsonObj.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().getAsString());
		}
	}
}
