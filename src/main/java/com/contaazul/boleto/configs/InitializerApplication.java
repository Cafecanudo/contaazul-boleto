package com.contaazul.boleto.configs;

import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.repositories.BoletoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Log4j2
@Component
public class InitializerApplication {

    @Autowired
    private BoletoRepository boletoRepository;

    public InitializerApplication insertInitials() {
        log.info("Inserting -> { Boleto com 5 dias de vencido }",
                boletoRepository.save(Boleto.builder().dueDate(LocalDate.now().minusDays(5))
                        .totalInCents(new BigDecimal(1000)).customer("Boleto 5 dias vencido").build()));

        log.info("Inserting -> { Boleto com mais de 10 dias de vencido }",
                boletoRepository.save(Boleto.builder().dueDate(LocalDate.now().minusDays(11)).status(StatusEnum.CANCELED)
                        .totalInCents(new BigDecimal(1000)).customer("Boleto 10 dias vencido").build()));
        return this;
    }

}
