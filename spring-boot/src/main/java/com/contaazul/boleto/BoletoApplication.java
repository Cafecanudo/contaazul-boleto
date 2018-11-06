package com.contaazul.boleto;

import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.repositories.BoletoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

import java.math.BigDecimal;
import java.time.LocalDate;

@EntityScan(basePackageClasses = {Jsr310Converters.class}, basePackages = "com.contaazul.boleto.entities")
@SpringBootApplication
@Log4j2
public class BoletoApplication implements CommandLineRunner {

    @Autowired
    private BoletoRepository boletoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BoletoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserting -> { CONTAAZUL ADMIN}",
                boletoRepository.save(new Boleto(1L, LocalDate.now(), new BigDecimal(100000), "Boleto Inicial", StatusEnum.PENDING)));
    }
}
