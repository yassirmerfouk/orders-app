package com.ym.notification_service.email;

import com.ym.notification_service.notification.NotificationType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;
    private final TemplateEngine engine;

    private final String from = "ym-orders@mail.com";

    public void sendEmail(
            String email,
            String fullName,
            NotificationType type,
            Map<String, Object> properties
    ) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                "UTF-8"
        );
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(type.getSubject());

        Context context = new Context();
        context.setVariables(properties);

        String template = engine.process(type.getTemplate(), context);

        mimeMessageHelper.setText(template, true);

        sender.send(mimeMessage);
    }
}
