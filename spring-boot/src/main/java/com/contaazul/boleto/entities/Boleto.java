package com.contaazul.boleto.entities;

import com.contaazul.boleto.entities.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_boleto")
public class Boleto {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    @EqualsAndHashCode.Exclude
    private LocalDate dueDate;

    @Column(name = "total_in_cents")
    private BigDecimal totalInCents;

    @EqualsAndHashCode.Exclude
    private String customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

}
