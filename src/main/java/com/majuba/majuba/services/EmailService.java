package com.majuba.majuba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    public void enviarCorreo(String to, String asunto,String cuerpo)  {

        new Thread(() -> {
            System.out.println("Enviando correo a "+to);
            try {

                //codigo para enviar el mail - setea el asunto, cuerpo,destinatario
                MimeMessage message = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
                helper.setFrom(from);
                helper.setTo(to);
                helper.setSubject(asunto);
                helper.setText(cuerpo,true);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(cuerpo,"text/html");

                String urlLogo = "C:\\Users\\JP\\Documents\\GitHub\\majuba\\src\\main\\resources\\static\\images\\logo.png";

                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.attachFile(urlLogo);
                imagePart.setContentID("<logoMajubaEncabezado>");
                imagePart.setDisposition(Part.INLINE);

                sender.send(message);

            } catch (MessagingException | IOException e) {
                e.printStackTrace(); // chequear el mensaje.
            }

        });

    }






}
