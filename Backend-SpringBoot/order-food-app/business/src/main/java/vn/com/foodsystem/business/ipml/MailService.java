//package vn.com.foodsystem.business.ipml;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//@Transactional
//public class MailService {
//
//	private JavaMailSender javaMailSender;
//
//	@Autowired
//	public MailService(JavaMailSender javaMailSender) {
//		this.javaMailSender = javaMailSender;
//	}
//
//	public void sendEmail(String toEmail, String subject, String content) throws MailException {
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(toEmail);
//		mail.setSubject(subject);
//		mail.setText(content);
//
//		javaMailSender.send(mail);
//	}
//
////	public void senEmailWithAttachment(String toEmail) throws MailException, MessagingException {
////		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
////
////		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
////
////		helper.setTo(toEmail);
////		helper.setSubject("Testing Mail API with Attachment");
////		helper.setText("Please find the attached document below.");
////
////		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
////		helper.addAttachment(classPathResource.getFilename(), classPathResource);
////
////		javaMailSender.send(mimeMessage);
////	}
//}
