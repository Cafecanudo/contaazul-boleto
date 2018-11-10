package com.contaazul.boleto.repositories;

import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BoletoRepositoryTest {

    public static final double VALOR_PADRAO_DE_BOLETO = 1000.00;
    public static final UUID ID_PADRAO = UUID.nameUUIDFromBytes("001".getBytes());

    @Autowired
    private BoletoRepository boletoRepository;

    @Test
    @DisplayName("Test para criar Boleto")
    public void criarBoletos() {
        Boleto boleto_1 = boletoRepository.save(Boleto.builder()
                .id(ID_PADRAO)
                .customer("Boleto 1")
                .dueDate(LocalDate.parse("2018-11-01"))
                .totalInCents(BigDecimal.valueOf(VALOR_PADRAO_DE_BOLETO))
                .build());

        assertNotNull(boleto_1.getId(), "Nao gerou ID do boleto!");
        assertNotNull(boleto_1.getStatus(), "Status nao pode ser nulo apos salvar boleto!");
        assertEquals(boleto_1.getStatus(), StatusEnum.PENDING, "Status nao pode ser diferente de PENDING quando e salvo!");
    }

    @Test
    @DisplayName("Test requireds dos field da entidade")
    public void naoCriarBoletos() {
        assertThrows(DataIntegrityViolationException.class, () -> boletoRepository.save(Boleto.builder()
                .dueDate(LocalDate.parse("2018-11-01"))
                .totalInCents(BigDecimal.valueOf(1000))
                .build()));

        assertThrows(DataIntegrityViolationException.class, () -> boletoRepository.save(Boleto.builder()
                .customer("Boleto 1")
                .totalInCents(BigDecimal.valueOf(1000))
                .build()));

        assertThrows(DataIntegrityViolationException.class, () -> boletoRepository.save(Boleto.builder()
                .customer("Boleto 1")
                .dueDate(LocalDate.parse("2018-11-01"))
                .build()));

        assertThrows(DateTimeParseException.class, () -> boletoRepository.save(Boleto.builder()
                .customer("Boleto 1")
                .dueDate(LocalDate.parse("2454545"))
                .totalInCents(BigDecimal.valueOf(1000))
                .build()));
    }

    @Test
    @DisplayName("Test lista de boletos")
    public void buscarBoletos() {
        Boleto boleto_1 = boletoRepository.save(Boleto.builder()
                .id(ID_PADRAO)
                .customer("Boleto 5")
                .dueDate(LocalDate.parse("2018-11-01"))
                .totalInCents(BigDecimal.valueOf(VALOR_PADRAO_DE_BOLETO))
                .build());

        Optional<List<Boleto>> listBoletos = Optional.ofNullable(boletoRepository.findAll());
        assertThat(listBoletos.get(), not(equalTo(Collections.EMPTY_MAP)));
        assertThat(listBoletos.get(), hasItem(boleto_1));
    }

    @Test
    @DisplayName("Test obter boleto por ID")
    public void obterBoletoPorId() {
        Optional<Boleto> boleto = boletoRepository.findById(ID_PADRAO);
        assertNotNull(boleto);
    }

}