package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para a entidade Abastecimento.
 * Esta interface utiliza o poder do Spring Data JPA para abstrair completamente as operações de persistência.
 * Ao herdar de JpaRepository, esta interface ganha um conjunto completo de métodos CRUD (save, findById, findAll, deleteById, etc.)
 * sem a necessidade de qualquer implementação manual.
 */
@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Integer> {

    /**
     * Declaração de um método de consulta personalizado, conhecido como "Query Method".
     * O Spring Data JPA tem um poderoso mecanismo que interpreta o nome do método e gera a consulta JPQL/SQL correspondente.
     *
     * Neste caso, "findAllByOrderByIdDesc" é traduzido para: "SELECT a FROM Abastecimento a ORDER BY a.id DESC".
     *
     * Esta abordagem permite a criação de consultas complexas de forma declarativa e segura,
     * melhorando a legibilidade e a manutenibilidade do código de acesso a dados.
     *
     * @return uma lista de todos os abastecimentos, ordenada pelo ID em ordem descendente (do mais recente para o mais antigo).
     */
    List<Abastecimento> findAllByOrderByIdDesc();
}
