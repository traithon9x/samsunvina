package com.samsun.samsunvina.services.interfaces;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.samsun.samsunvina.entities.Image;

public interface EmailService {
    //send simple email
    void sendSimpleMessage(String to, String subject,String text);

    //simple email using template
    void sendSimpleMessageUsingTemplate(String to,String subject,SimpleMailMessage template, String ...templateArgs);

    //send simple email attacht one file
    void sendMessageWithAttachment(String to,String subject,String text, String pathToAttachment);

    //send email attach list images
    void sendMessageWithAttachmentImages(String to,String subject, String text, List<Image> images);
}
