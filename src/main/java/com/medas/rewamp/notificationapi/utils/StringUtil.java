package com.medas.rewamp.notificationapi.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

/**
 * String formatting
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 19, 2020
 *
 */
@Slf4j
public class StringUtil {

	private StringUtil() {
	}

	/**
	 * Setting data to base template
	 * 
	 * @param birthdayTemplate
	 * @param data
	 * @return
	 */
	public static String getString(String content, Object... datas) {
		String formatted = content;
		String dataHolder = "\\{\\}";
		if (datas != null) {
			for (Object data : datas) {
				formatted = formatted.replaceFirst(dataHolder, data.toString());
			}
		}
		return formatted;
	}
	
	/**
	 * To encode content string
	 * 
	 * @param content
	 * @return
	 */
	public static CharSequence encodeString(String content) {
		try {
			return URLEncoder.encode(content, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error("Error on encoding string: {}", e.getMessage());
			return content;
		}
	}
}
