package com.majuba.majuba;

import com.majuba.majuba.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MajubaApplication implements CommandLineRunner {
    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(MajubaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        emailService.enviarCorreo("juanpablogallart@gmail.com", "correo de prueba", "hola este es un correo de prueba");
    }
}
