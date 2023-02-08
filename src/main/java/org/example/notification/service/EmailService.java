package org.example.notification.service;

import freemarker.template.Configuration;
import org.example.notification.entity.Email;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class EmailService {
    Logger logger = Logger.getGlobal();
    private final JavaMailSender sender;
    private final Configuration configuration;
    private String from;

    public EmailService(JavaMailSender sender,
                        @Qualifier("freeMarker") Configuration configuration,
                        @Value("${spring.mail.username}") final String from) {
        this.sender = sender;
        this.configuration = configuration;
        this.from = from;
    }


    public void send(Email email) {
        MimeMessage mimeMailMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setSubject(email.getSubject());
            helper.setFrom(from);
            helper.setTo(email.getTo());
            helper.setText(geContentFromTemplate(email.toMap()), true);
            sender.send(mimeMailMessage);
        } catch(Exception exception) {
            logger.info("Error while sending an email to " + email.getTo());
        }
    }

    public String geContentFromTemplate(Map < String, String >model)     {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("email.html"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
