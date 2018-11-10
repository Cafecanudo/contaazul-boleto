package com.contaazul.boleto;

import com.contaazul.boleto.configs.InitializerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication(scanBasePackages = "com.contaazul.boleto")
@EntityScan(basePackageClasses = {Jsr310Converters.class}, basePackages = "com.contaazul.boleto.entities")
public class BoletoApplication implements CommandLineRunner {

    @Autowired
    private InitializerApplication initializerApplication;

    public static void main(String[] args) {
        SpringApplication.run(BoletoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initializerApplication.insertInitials();
    }

}
