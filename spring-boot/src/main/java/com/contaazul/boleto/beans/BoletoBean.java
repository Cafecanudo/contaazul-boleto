package com.contaazul.boleto.beans;

import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.validations.annotations.BrazilMoneyValidation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoletoBean implements Serializable {

    private static final long serialVersionUID = -8876424847925784788L;

    @Size(max = 60, message = "Max 60 characters")
    private String id;

    @NotNull(message = "Can not be empty")
    @JsonProperty(value = "due_date", access = JsonProperty.Access.READ_ONLY)
    private LocalDate dueDate;

    @NotNull(message = "Can not be empty")
    @JsonProperty(value = "total_in_cents")
    @Size(max = 15, message = "Max 15 characters")
    @BrazilMoneyValidation
    private String totalInCents;

    @NotNull(message = "Can not be empty")
    @Size(max = 100, message = "Max 100 characters")
    private String customer;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusEnum status;

    @JsonProperty(value = "payment_date", access = JsonProperty.Access.READ_ONLY)
    private LocalDate paymentDate;

    private LocalDate fine;
}
