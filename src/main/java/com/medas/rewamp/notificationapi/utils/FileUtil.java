package com.medas.rewamp.notificationapi.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility for files
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 23, 2020
 *
 */
@Slf4j
@Component
public class FileUtil {

	/**
	 * Method to save base64 content to file
	 * 
	 * @param fileData
	 * @param filePath
	 * @throws IOException
	 */
	public void saveBase64ToFile(String fileData, String filePath) throws IOException {
		byte[] data = null;
		try {
			data = Base64.getDecoder().decode(fileData);
		} catch (IllegalArgumentException iae) {
			log.error("Error on decoding base64: ", iae);
			throw iae;
		}
		try (OutputStream stream = new FileOutputStream(filePath)) {
			stream.write(data);
		} catch (IOException e) {
			log.error("Error on saving file: ", e);
			throw e;
		}
	}
}
