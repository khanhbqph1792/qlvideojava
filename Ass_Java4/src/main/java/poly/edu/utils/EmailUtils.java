package poly.edu.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import poly.edu.domain.Email;

public class EmailUtils {
	public static void send(Email email) throws Exception {
		Properties pro = new Properties();
		pro.put("mail.smtp.ssl.protocols", "TLSv1.2");
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");
		pro.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		pro.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(pro, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getForm(), email.getFromPassword());
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email.getForm()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
			message.setSubject(email.getSubject());
			message.setContent(email.getContent(), "text/html; charset=utf-8");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
