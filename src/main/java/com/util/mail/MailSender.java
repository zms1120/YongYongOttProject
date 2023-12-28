package com.util.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailSender {
 
	public void mailSending(MailDTO mailDto) {
		Properties props = new Properties();

		props.setProperty("mail.smtp.protocol", mailDto.getProtocol());
		props.setProperty("mail.smtp.host", mailDto.getHost());
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		// props.setProperty("mail.smtp.timeout", "9000");

		// 보내는 사람 계정 정보 설정
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailDto.getFrom(), mailDto.getPassword());
			}
		});

		// 메일 내용 작성
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(mailDto.getFrom()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDto.getTo()));
			msg.setSubject("용용플레이 회원가입 인증");
			msg.setText("인증번호 입니다. \n\n " + mailDto.getNumber() + "\n\n화면으로 돌아가 인증번호를 입력해 주세요.");

			// 메일 보내기
			Transport.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
