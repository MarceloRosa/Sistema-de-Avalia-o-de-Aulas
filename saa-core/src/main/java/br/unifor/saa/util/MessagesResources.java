/**
 * 
 */
package br.unifor.saa.util;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
public class MessagesResources {
	
	private static Locale locale;
	
	private static ResourceBundle getProp() throws IOException {
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		return rb;
	}
	
	public static String getMessages(String key) {
		try {
			return getProp().getString(key);
		} catch (IOException e) {
			return null;
		}
	}

	public static void setLocale(Locale locale) {
		MessagesResources.locale = locale;
	}
	
}
