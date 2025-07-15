package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a entidade Combustivel.
 * Esta interface é um exemplo claro da produtividade ganha com o Spring Data JPA.
 *
 * Conceitos Chave:
 * - @Repository: Anotação que marca esta interface como um componente Spring, permitindo a injeção de dependência.
 *
 * - extends JpaRepository<Combustivel, Integer>: Este é o núcleo da abstração.
 * - O primeiro parâmetro genérico, `Combustivel`, indica a entidade que este repository gere.
 * - O segundo, `Integer`, especifica o tipo da chave primária (@Id) dessa entidade.
 *
 * Vantagens desta abordagem sobre o DAO manual:
 * - Redução Drástica de Código: Centenas de linhas de código JDBC repetitivo são eliminadas.
 * - Segurança e Otimização: O Spring Data JPA gera implementações otimizadas e seguras,
 * prevenindo ataques comuns como SQL Injection.
 * - Produtividade: O desenvolvimento da camada de dados torna-se quase instantâneo.
 */
@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Integer> {
    // Não é necessário escrever nenhum método aqui para as operações básicas de CRUD.
}
