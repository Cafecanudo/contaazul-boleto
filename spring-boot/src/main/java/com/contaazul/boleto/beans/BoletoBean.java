package com.contaazul.boleto.beans;

import com.contaazul.boleto.entities.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BoletoBean implements Serializable {

    private static final long serialVersionUID = -8876424847925784788L;

    private Long id;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("total_in_cents")
    private BigDecimal totalInCents;
    private String customer;

    private StatusEnum status;
}
