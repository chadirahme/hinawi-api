package com.hinawi.api.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by chadirahme on 2/3/20.
 */
public class UserConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "NO_USER";
    }
}
