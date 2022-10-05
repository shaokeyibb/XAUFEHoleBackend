package cn.xctra.xaufeholebackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public MailService(MailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    public void sendMail(String receiver, String subject, String content) {
        mailSender.send(new SimpleMailMessage() {{
            setFrom(sender);
            setTo(receiver);
            setSubject(subject);
            setText(content);
        }});
    }
}
