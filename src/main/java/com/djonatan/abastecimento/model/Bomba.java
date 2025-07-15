package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a entidade 'Bomba', mapeada para a tabela 'bomba' no banco de dados.
 * Esta entidade demonstra um relacionamento de cardinalidade Many-to-One com a entidade Combustivel.
 *
 * Anotações JPA utilizadas:
 * - @Entity, @Table, @Id, @GeneratedValue: Possuem a mesma função explicada na entidade Combustivel.
 * - @ManyToOne: Define um relacionamento de "muitos-para-um". Isto indica que muitas instâncias de 'Bomba'
 * podem estar associadas a uma única instância de 'Combustivel'. O 'FetchType' padrão é EAGER,
 * o que significa que os dados do combustível relacionado são carregados juntamente com os da bomba.
 * - @JoinColumn: Especifica qual coluna na tabela 'bomba' é a chave estrangeira que se conecta à
 * tabela 'combustivel'. O atributo 'name = "combustivel_id"' mapeia diretamente para a coluna
 * do banco de dados, e 'nullable = false' impõe a restrição de que uma bomba deve,
 * obrigatoriamente, ter um combustível associado.
 */
@Entity
@Table(name = "bomba")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bomba {

    /**
     * Identificador único da bomba.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nome descritivo da bomba (ex: "Bomba 01").
     */
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    /**
     * O tipo de combustível que esta bomba fornece.
     * Este atributo representa o lado "muitos" do relacionamento com a entidade Combustivel.
     */
    @ManyToOne
    @JoinColumn(name = "combustivel_id", nullable = false)
    private Combustivel combustivel;
}
