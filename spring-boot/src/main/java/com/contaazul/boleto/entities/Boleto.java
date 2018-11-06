package com.contaazul.boleto.entities;

import com.contaazul.boleto.entities.enums.StatusEnum;
import lombok.*;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date", nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDate dueDate;

    @Column(name = "total_in_cents", nullable = false)
    private BigDecimal totalInCents;

    @EqualsAndHashCode.Exclude
    private String customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

}
