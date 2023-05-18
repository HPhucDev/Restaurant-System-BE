package vn.com.foodsystem.business.ipml;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Value("${email.properties.mail.smtp.auth}")
	private String smtpAuth;

	@Value("${email.properties.mail.smtp.starttls.enable}")
	private String smtpStarttlsEnable;

	@Value("${email.host}")
	private String smtpHost;

	@Value("${email.port}")
	private String smtpPort;

	@Value("${email.port}")
	private String serverHost;

	public void sendVerifyRegistrationEmail(String toAddr, String token) {
		try {
			Properties props = initProperties();
			Session session = initSession(props, username, password);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
			message.setSubject("Please verify your registration");
			message.setContent(buildConfirmMailContent(toAddr, token), "text/html");

			Transport.send(message);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	public void sendResetPasswordMail(String toAddr, int otpCode) {
		try {
			Properties props = initProperties();
			Session session = initSession(props, username, password);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
			message.setSubject("Please verify your Reset password");
			message.setContent("Dear " + toAddr + "<br>"//
					+ "Here is your OTP code: " + otpCode +"<br>"//
					+ "Thank you,<br>" //
					, "text/html");

			Transport.send(message);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	private String buildConfirmMailContent(String email, String token) {
		String siteUrl = serverHost + "/register/verify?token=" + token;
		return "Dear " + email //
				+ ". Please click the link below to verify your registration:<br>" //
				+ "<h3><a href=\"" + siteUrl + "\" target=\"_self\">VERIFY</a></h3>" //
				+ "Thank you,<br>" //
				+ "Meet your Idol team.";
	}

	private Session initSession(Properties props, final String username, final String password) {
		return Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	private Properties initProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		return props;
	}
}
