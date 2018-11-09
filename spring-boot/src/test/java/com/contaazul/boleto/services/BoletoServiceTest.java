package com.contaazul.boleto.services;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.repositories.BoletoRepository;
import com.contaazul.boleto.services.impl.BoletoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DisplayName("Test de servico de Boletos")
public class BoletoServiceTest {

    @MockBean
    private BoletoRepository boletoRepository;

    @Autowired
    private BoletoService boletoService;

    @BeforeEach
    public void inicializarMockito() {
        BDDMockito.given(boletoRepository.save(Mockito.any(Boleto.class)))
                .willReturn(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 1")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build());

        BDDMockito.given(boletoRepository.findById(Mockito.any(UUID.class)))
                .willReturn(Optional.of(Boleto.builder()
                        .id(UUID.nameUUIDFromBytes("001".getBytes()))
                        .customer("BOLETO 2")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .status(StatusEnum.PENDING)
                        .build()));

        /*BDDMockito.given(boletoService.criarBoleto(Mockito.any(BoletoBean.class)))
                .willReturn(BoletoBean.builder()
                        .customer("BOLETO 1")
                        .totalInCents(BigDecimal.valueOf(1000.00))
                        .dueDate(LocalDate.parse("2018-11-01"))
                        .build());*/
    }

    @Test
    void criarBoleto() {
        BoletoBean b = boletoService.criarBoleto(BoletoBean.builder()
                .customer("BOLETO 1")
                .totalInCents(BigDecimal.valueOf(1000.00))
                .dueDate(LocalDate.parse("2018-11-01"))
                .build());

        assertNotNull(b.getId(), "Nao gerou ID do boleto!");
        assertEquals(b.getStatus(), StatusEnum.PENDING, "Status nao pode ser diferente de PENDING quando e salvo!");
    }

    @Test
    void detalhesBoleto() throws Exception {

        PowerMockito.doReturn(9L).when(PowerMockito
                .spy(new BoletoServiceImpl()),"diferencaDias", LocalDate.now().minusDays(10))
//                .thenReturn(10L)
        ;

        BoletoBean b = boletoService.detalhesBoleto(UUID.nameUUIDFromBytes("001".getBytes()).toString());
        System.out.println(b);

        assertNotNull(b, "Nao encontrou o boleto!");

    }

    @Test
    void pagarBoleto() {
    }

    @Test
    void cancelarBoleto() {
    }
}