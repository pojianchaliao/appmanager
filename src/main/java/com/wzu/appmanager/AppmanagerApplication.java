package com.wzu.appmanager;

import org.apache.log4j.BasicConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzu.appmanager.dao")
public class AppmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppmanagerApplication.class, args);
        BasicConfigurator.configure();
    }

}
