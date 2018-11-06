package com.contaazul.boleto.beans;

import com.contaazul.boleto.entities.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletoBean implements Serializable {

    private static final long serialVersionUID = -8876424847925784788L;

    private Long id;

    @NotEmpty
    @JsonProperty("due_date")
    private LocalDate dueDate;

    @NotEmpty
    @JsonProperty("total_in_cents")
    private String totalInCents;

    @NotEmpty
    private String customer;

    @NotEmpty
    private StatusEnum status;
}
