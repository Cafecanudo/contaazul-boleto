package com.contaazul.boleto.beans;

import com.contaazul.boleto.beans.convertes.BigDecimalToStringSerializer;
import com.contaazul.boleto.beans.convertes.LocalDateDeserializer;
import com.contaazul.boleto.beans.convertes.StatusEnumDeserializer;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.validations.annotations.BrazilMoneyValidation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Boleto")
public class BoletoBean implements Serializable {

    private static final long serialVersionUID = -8876424847925784788L;

    @ApiModelProperty(notes = "ID do boleto")
    @Size(max = 60, message = "Max 60 characters")
    private String id;

    @ApiModelProperty(notes = "Data de vencimento do boleto", required = true)
    @NotNull(message = "Can not be empty")
    @JsonProperty(value = "due_date")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;

    @ApiModelProperty(notes = "Valor da divída", required = true)
    @NotNull(message = "Can not be empty")
    @JsonProperty("total_in_cents")
    @BrazilMoneyValidation
    @JsonSerialize(converter = BigDecimalToStringSerializer.class)
    private BigDecimal totalInCents;

    @ApiModelProperty(notes = "Descrição do boleto", required = true)
    @NotNull(message = "Can not be empty")
    @Size(max = 100, message = "Max 100 characters")
    private String customer;

    @ApiModelProperty(notes = "Status do Boleto [PENDING, PAID, CANCELED]")
    @JsonDeserialize(using = StatusEnumDeserializer.class)
    private StatusEnum status;

    @ApiModelProperty(notes = "Data de pagamento")
    @JsonProperty(value = "payment_date")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    @ApiModelProperty(notes = "Não sei para que serve(Ainda)")
    private String fine;
}