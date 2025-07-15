package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "combustivel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combustivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "preco_por_litro", nullable = false, precision = 10, scale = 3)
    private BigDecimal precoPorLitro;
}
