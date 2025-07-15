package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para a entidade Abastecimento.
 * Fornece a abstração completa para as operações de persistência dos registos de abastecimento.
 *
 * Além do CRUD padrão, o Spring Data JPA permite a criação de consultas personalizadas
 * simplesmente declarando a assinatura do método. Por exemplo, poderíamos adicionar:
 * `List<Abastecimento> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);`
 * e o Spring implementaria a busca por data automaticamente.
 *
 * Para a listagem, adicionamos um método personalizado para ordenar os resultados.
 */
@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Integer> {

    /**
     * Declaração de um método de consulta personalizado (Query Method).
     * O Spring Data JPA interpreta o nome do método e gera a consulta SQL correspondente.
     * "findAllByOrderByIdDesc" traduz-se em "SELECT * FROM abastecimento ORDER BY id DESC".
     * Isto permite-nos listar os abastecimentos do mais recente para o mais antigo.
     * @return uma lista de todos os abastecimentos, ordenada por ID de forma descendente.
     */
    List<Abastecimento> findAllByOrderByIdDesc();
}
