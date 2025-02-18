package Resources;

import java.util.Properties;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMessage;

public class OTPRetreival {
	public static String getOTPFromEmail(String email, String password) {
		String otp = null;
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imap");
		properties.put("mail.imap.host", "imap.gmail.com");
		properties.put("mail.imap.port", "993");
		properties.put("mail.imap.ssl.enable", "true");

		try {
			Session session = Session.getInstance(properties);
			Store store = session.getStore();
			store.connect("imap.gmail.com", email, password);

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			Message[] messages = inbox.getMessages();
			for (int i = messages.length - 1; i >= 0; i--) { // Start from the latest email
				MimeMessage message = (MimeMessage) messages[i];
				String subject = message.getSubject();
				if (subject.contains("Your OTP")) { // Customize based on email subject
					String content = message.getContent().toString();
					otp = content.replaceAll("[^0-9]", ""); // Extract OTP (assuming it's numeric)
					break;
				}
			}

			inbox.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return otp;
	}
}
