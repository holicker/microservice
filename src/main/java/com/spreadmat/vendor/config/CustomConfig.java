package com.spreadmat.vendor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomConfig {
    @Value("${your.name}")
    private String yourName;

    public String getYourName(){
        return yourName;
    }
}
