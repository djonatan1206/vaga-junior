package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "abastecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abastecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bomba_id", nullable = false)
    private Bomba bomba;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "litros", nullable = false, precision = 10, scale = 3)
    private BigDecimal litros;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
}
