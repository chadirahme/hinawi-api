package com.hinawi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by chadirahme on 3/12/20.
 */
@Getter
@Setter
public class MailModel {

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List<Object> attachments;

    public MailModel() {
        contentType = "text/plain";
    }
}
