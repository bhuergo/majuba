package com.majuba.majuba;

import com.majuba.majuba.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MajubaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajubaApplication.class, args);
    }

}
