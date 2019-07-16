package com.hinawi.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig implements PasswordEncoder{

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
    //@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//        };
//    }
}
