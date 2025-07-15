package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Representa a entidade 'Combustivel', mapeada para a tabela 'combustivel' no banco de dados.
 *
 * Anotações JPA utilizadas:
 * - @Entity: Marca esta classe como uma entidade que pode ser gerida pelo Hibernate.
 * - @Table: Especifica o nome exato da tabela no banco de dados.
 * - @Id: Define que o campo 'id' é a chave primária da tabela.
 * - @GeneratedValue: Configura a estratégia de geração da chave primária (AUTO_INCREMENT).
 * - @Column: Mapeia um atributo da classe para uma coluna específica na tabela.
 */
@Entity
@Table(name = "combustivel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combustivel {

    /**
     * Identificador único do combustível.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nome do tipo de combustível (ex: "Gasolina Aditivada").
     */
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    /**
     * O preço por litro do combustível.
     * O uso de `precision` e `scale` garante a correspondência com o tipo DECIMAL do banco.
     */
    @Column(name = "preco_por_litro", nullable = false, precision = 10, scale = 3)
    private BigDecimal precoPorLitro;
}
