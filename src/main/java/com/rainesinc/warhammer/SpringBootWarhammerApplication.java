package com.rainesinc.warhammer;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class SpringBootWarhammerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWarhammerApplication.class, args);
    }

}