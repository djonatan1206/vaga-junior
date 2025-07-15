package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bomba")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bomba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "combustivel_id", nullable = false)
    private Combustivel combustivel;
}
