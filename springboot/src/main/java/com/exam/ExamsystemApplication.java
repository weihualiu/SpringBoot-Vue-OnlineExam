package com.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExamsystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ExamsystemApplication.class, args);
    }

    // 打war包需要重写该方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExamsystemApplication.class);
    }
}

