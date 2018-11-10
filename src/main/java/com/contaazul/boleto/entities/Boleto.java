package com.contaazul.boleto.entities;

import com.contaazul.boleto.entities.enums.StatusEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "tb_boleto")
@NoArgsConstructor
@AllArgsConstructor
public class Boleto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UUID", strategy=GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private UUID id;

    @EqualsAndHashCode.Exclude
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @EqualsAndHashCode.Exclude
    @Column(name = "total_in_cents", nullable = false)
    private BigDecimal totalInCents;

    @Column(length = 100, nullable = false)
    private String customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @EqualsAndHashCode.Exclude
    private LocalDate paymentDate;

    @EqualsAndHashCode.Exclude
    private String fine;

    @PrePersist
    private void setStatusDefault() {
        this.status = Optional.ofNullable(this.status).orElse(StatusEnum.PENDING);
    }

}
