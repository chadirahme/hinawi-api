package com.hinawi.api.config;

import ch.qos.logback.classic.PatternLayout;

/**
 * Created by chadirahme on 2/3/20.
 * https://www.codelord.net/2010/08/27/logging-with-a-context-users-in-logback-and-spring-security/
 * https://blog.miyozinc.com/spring/880/
 */
public class PatternLayoutWithUserContext extends PatternLayout {
    static {
        PatternLayout.defaultConverterMap.put(
                "user", UserConverter.class.getName());
//        PatternLayout.defaultConverterMap.put(
//                "session", SessionConverter.class.getName());
    }
}
