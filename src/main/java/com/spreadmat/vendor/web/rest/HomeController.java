package com.spreadmat.vendor.web.rest;

import com.spreadmat.vendor.config.CustomConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Data
@RequestMapping(path = "/home", produces = "application/json")
public class HomeController {
    @Autowired
    private final CustomConfig customConfig;

    @GetMapping("/name")
    public String getYourName(){
        return "your name is " + customConfig.getYourName();
    }

    @GetMapping("/")
    public void hello(){
        log.info("하하하");
    }
}
