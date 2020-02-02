package com.hinawi.api.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

@Component
public class ActuatorsCustomEndpoint implements HealthIndicator {
    @Override
    public Health health() {
        return Health.unknown().build();
    }

//    @Override
//    public String id() {
//        return "custom-endpoint";
//    }
//
//    @Override
//    public boolean enableByDefault() {
//        return false;
//    }
//
//    @Override
//    public Class<? extends Annotation> annotationType() {
//        return null;
//    }
}
