package com.yl.ms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 001
 */
@SpringBootApplication
@MapperScan("com.yl.ms.dao")
@ComponentScan(basePackages = {"com.yl.ms.service","com.yl.ms.controller","com.yl.ms.utils","com.yl.ms.config"})
public class MsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApplication.class, args);
    }

}
