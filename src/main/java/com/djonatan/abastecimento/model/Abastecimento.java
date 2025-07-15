package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa a entidade 'Abastecimento', mapeada para a tabela 'abastecimento' no banco de dados.
 * Esta é a entidade transacional central do sistema, registando cada operação de abastecimento realizada.
 *
 * Anotações JPA utilizadas:
 * - @Entity: Marca esta classe como uma entidade persistente, gerida pelo Hibernate.
 * - @Table: Especifica o nome da tabela correspondente no banco de dados.
 *
 * Anotações Lombok:
 * - @Data: Gera automaticamente os métodos boilerplate (getters, setters, toString, equals, hashCode),
 * mantendo a classe limpa e focada na representação dos dados.
 * - @NoArgsConstructor: Gera um construtor sem argumentos, um requisito da especificação JPA.
 * - @AllArgsConstructor: Gera um construtor com todos os campos, útil para testes e instanciação.
 *
 * Princípio de Design - Entidade Anémica:
 * Seguindo as boas práticas de uma arquitetura Spring, esta entidade é "anémica", ou seja,
 * não contém lógica de negócio (como cálculos). A sua única responsabilidade é representar
 * fielmente os dados da tabela. A lógica de negócio foi delegada para a Camada de Serviço (`AbastecimentoService`).
 */
@Entity
@Table(name = "abastecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abastecimento {

    /**
     * Identificador único do abastecimento.
     * - @Id: Marca este campo como a chave primária da entidade.
     * - @GeneratedValue(strategy = GenerationType.IDENTITY): Delega a geração do valor
     * desta chave para o banco de dados (ex: AUTO_INCREMENT do MySQL).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * A bomba onde o abastecimento foi realizado.
     * - @ManyToOne: Define um relacionamento de muitos-para-um com a entidade Bomba.
     * Muitos abastecimentos podem ocorrer numa única bomba.
     * - @JoinColumn: Especifica a coluna de chave estrangeira ('bomba_id') nesta tabela
     * que se liga à chave primária da tabela 'bomba'.
     */
    @ManyToOne
    @JoinColumn(name = "bomba_id", nullable = false)
    private Bomba bomba;

    /**
     * A data e hora exatas em que o abastecimento foi registado.
     * - @Column: Mapeia este atributo para a coluna 'data' do tipo DATETIME no banco de dados.
     */
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    /**
     * A quantidade de combustível abastecida, em litros.
     * - @Column: Mapeia para a coluna 'litros', definindo a precisão e a escala para
     * garantir a consistência com o tipo DECIMAL do banco de dados.
     */
    @Column(name = "litros", nullable = false, precision = 10, scale = 3)
    private BigDecimal litros;

    /**
     * O custo total do abastecimento, em Reais.
     * - @Column: Mapeia para a coluna 'valor_total', com a precisão e escala adequadas
     * para valores monetários.
     */
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
}
