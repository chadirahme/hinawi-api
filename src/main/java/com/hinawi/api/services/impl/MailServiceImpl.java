package com.hinawi.api.services.impl;

import com.hinawi.api.dto.MailModel;
import com.hinawi.api.services.MailService;
import com.hinawi.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by chadirahme on 3/12/20.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(MailModel mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("info@hinawi.ae");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(mail.getMailContent(), true);

        helper.setTo(mail.getMailTo());

        if(!StringUtils.isEmptyString(mail.getMailBcc()))
        helper.setBcc(mail.getMailBcc());

        if(!StringUtils.isEmptyString(mail.getMailCc()))
            helper.setCc(mail.getMailCc());

        helper.setSubject(mail.getMailSubject());

        javaMailSender.send(message);
    }
}
