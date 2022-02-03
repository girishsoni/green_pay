package org.greenpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GreenPayApplication {
    public static void main(String[] args){
        SpringApplication.run(GreenPayApplication.class, args);
    }
}
