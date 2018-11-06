package com.contaazul.boleto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@EntityScan(basePackageClasses = {Jsr310Converters.class}, basePackages = "com.contaazul.boleto.entities")
@SpringBootApplication
public class BoletoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoletoApplication.class, args);
    }

}
