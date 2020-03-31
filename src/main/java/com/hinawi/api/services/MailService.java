package com.hinawi.api.services;

import com.hinawi.api.dto.MailModel;

import javax.mail.MessagingException;

/**
 * Created by chadirahme on 3/12/20.
 */

public interface MailService {

    void sendEmail(MailModel mail) throws MessagingException;
}
