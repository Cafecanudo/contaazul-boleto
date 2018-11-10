package com.contaazul.boleto.resources.impl;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.utils.AbstractTest;
import com.google.common.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoletoResourceTest extends AbstractTest {

    @Before
    public void init() {

    }

    @Test
    public void listarBoletos() throws Exception {
        Type listType = new TypeToken<List<BoletoBean>>() {
        }.getType();

        String content = mvc.perform(get("/bankslips").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<BoletoBean> list = super.toObject(content, listType);
        assertThat(list, not(empty()));
        assertThat(list.size(), greaterThan(3));
    }

    @Test
    public void criarBoleto() throws Exception {
        BoletoBean b = BoletoBean.builder()
                .customer("Boleto 1")
                .dueDate(LocalDate.now().minusDays(5))
                .totalInCents(BigDecimal.valueOf(1254.88))
                .build();

        String content = mvc.perform(
                post("/bankslips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(b)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_B = toObject(content, BoletoBean.class);
        assertNotNull(resp_B);
        assertNotNull(resp_B.getId());
        assertEquals(resp_B.getTotalInCents().unscaledValue().toString(), "125488");
        assertEquals(resp_B.getStatus(), StatusEnum.PENDING);
    }

    @Test
    public void detalhesBoleto() throws Exception {
        mvc.perform(get("/bankslips/" + UUID.randomUUID().toString()))
                .andExpect(status().isNotFound());


        BoletoBean b = BoletoBean.builder()
                .customer("Boleto 2")
                .dueDate(LocalDate.now().minusDays(5))
                .totalInCents(BigDecimal.valueOf(1254.88))
                .build();

        String content_1 = mvc.perform(
                post("/bankslips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(b)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_A = toObject(content_1, BoletoBean.class);

        String content_2 = mvc.perform(get("/bankslips/" + resp_A.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_B = toObject(content_2, BoletoBean.class);
        //Testar se calculo de juros foi alterado
        assertEquals(resp_B.getTotalInCents(), BigDecimal.valueOf(128625));
    }

    @Test
    public void pagarBoleto() throws Exception {
        BoletoBean b = BoletoBean.builder()
                .customer("Boleto 4")
                .dueDate(LocalDate.now().minusDays(5))
                .totalInCents(BigDecimal.valueOf(1254.88))
                .build();

        String content_1 = mvc.perform(
                post("/bankslips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(b)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_A = toObject(content_1, BoletoBean.class);

        mvc.perform(post("/bankslips/" + resp_A.getId() + "/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payment_date\":\" " + LocalDate.now() + "\"}"))
                .andExpect(status().isNoContent());

        //Verificar se esta pago
        String content_2 = mvc.perform(get("/bankslips/" + resp_A.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_B = toObject(content_2, BoletoBean.class);
        assertEquals(resp_B.getStatus(), StatusEnum.PAID);
        assertNotNull(resp_B.getPaymentDate());
    }

    @Test
    public void cancelarBoleto() throws Exception {
        BoletoBean b = BoletoBean.builder()
                .customer("Boleto 5")
                .dueDate(LocalDate.now().minusDays(5))
                .totalInCents(BigDecimal.valueOf(1254.88))
                .build();

        String content_1 = mvc.perform(
                post("/bankslips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(b)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_A = toObject(content_1, BoletoBean.class);

        mvc.perform(delete("/bankslips/" + resp_A.getId()))
                .andExpect(status().isNoContent());

        //Verificar se esta pago
        String content_2 = mvc.perform(get("/bankslips/" + resp_A.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BoletoBean resp_B = toObject(content_2, BoletoBean.class);
        assertEquals(resp_B.getStatus(), StatusEnum.CANCELED);

    }
}