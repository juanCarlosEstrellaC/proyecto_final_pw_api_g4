package com.example.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarCorreo(String destinatario, String asunto, String contenido) {
      /*  jakarta.mail.internet.MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenido, true);
        emailSender.send(message);*/

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(destinatario);
        email.setSubject(asunto);
        email.setText(contenido);

        emailSender.send(email);
    }
}