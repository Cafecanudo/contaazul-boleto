package com.contaazul.boleto.services;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.exceptions.NoResultExceptionApi;
import com.contaazul.boleto.exceptions.UnprocessableEntityException;
import com.contaazul.boleto.repositories.BoletoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("Test de servico de Boletos")
public class BoletoServiceTest {

    @MockBean
    private BoletoRepository boletoRepository;

    @Autowired
    private BoletoService boletoService;

    @Before
    public void inicializarMockito() {
        BDDMockito.given(boletoRepository.save(Boleto.builder().totalInCents(BigDecimal.valueOf(1000)).build()))
                .willReturn(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 1")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build());
    }

    @Test
    @DisplayName("Test criar de boleto")
    public void criarBoleto() {
        BoletoBean b = boletoService.criarBoleto(BoletoBean.builder().totalInCents(BigDecimal.valueOf(1000)).build());

        assertNotNull(b.getId(), "Nao gerou ID do boleto!");
        assertEquals(b.getStatus(), StatusEnum.PENDING, "Status nao pode ser diferente de PENDING quando e salvo!");
    }

    @Test
    @DisplayName("Test detalhes de boleto")
    public void detalhesBoleto() {
        BDDMockito.given(boletoRepository.findById(UUID.nameUUIDFromBytes("001".getBytes())))
                .willReturn(Optional.of(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 2")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build()));

        assertThrows(NoResultExceptionApi.class, () ->
                        boletoService.detalhesBoleto(UUID.nameUUIDFromBytes("NAO EXISTE".getBytes()).toString()),
                "Deve retornar error");

        assertNotNull(boletoService.detalhesBoleto(UUID.nameUUIDFromBytes("001".getBytes()).toString()),
                "Nao encontrou o boleto!");
    }

    @Test
    @DisplayName("Test pagamento de Boleto")
    public void pagarBoleto() {
        BDDMockito.given(boletoRepository.findById(UUID.nameUUIDFromBytes("001".getBytes())))
                .willReturn(Optional.of(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 2")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.CANCELED)
                        .build()));

        assertThrows(UnprocessableEntityException.class, () ->
                        boletoService.pagarBoleto(UUID.nameUUIDFromBytes("001".getBytes()).toString(), LocalDate.now()),
                "Deve retornar uma exceção caso boleto esteja cancelado!");

        BDDMockito.given(boletoRepository.findById(UUID.nameUUIDFromBytes("001".getBytes())))
                .willReturn(Optional.of(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 2")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build()));

        Boleto boleto = boletoService.pagarBoleto(UUID.nameUUIDFromBytes("001".getBytes()).toString(), LocalDate.now());
        assertNotNull(boleto);
        assertEquals(boleto.getStatus(), StatusEnum.PAID);
        assertEquals(boleto.getPaymentDate(), LocalDate.now());
    }

    @Test
    @DisplayName("Test cancelamento do boleto")
    public void cancelarBoleto() {
        BDDMockito.given(boletoRepository.findById(UUID.nameUUIDFromBytes("001".getBytes())))
                .willReturn(Optional.of(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 2")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build()));

        Boleto boleto = boletoService.cancelarBoleto(UUID.nameUUIDFromBytes("001".getBytes()).toString());
        assertNotNull(boleto);
        assertEquals(boleto.getStatus(), StatusEnum.CANCELED);
    }
}