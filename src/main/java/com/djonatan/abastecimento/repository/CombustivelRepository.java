package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a entidade Combustivel.
 * Esta interface utiliza o poder do Spring Data JPA para abstrair completamente o acesso a dados.
 *
 * Conceitos Chave:
 * - @Repository: Anotação que marca esta interface como um componente Spring do tipo Repository.
 * Isto permite que o Spring a detete (component scanning) e a injete em outras classes
 * (como os nossos futuros Services e Controllers) usando @Autowired.
 *
 * - extends JpaRepository<Combustivel, Integer>: Este é o núcleo da "magia" do Spring Data JPA.
 * - Ao herdar de JpaRepository, a nossa interface `CombustivelRepository` ganha, sem necessidade de
 * implementação, um conjunto completo de métodos CRUD (Create, Read, Update, Delete).
 * - Métodos como `save()`, `findById()`, `findAll()`, `deleteById()` e muitos outros já estão
 * disponíveis para uso imediato.
 * - Os parâmetros genéricos `<Combustivel, Integer>` dizem ao Spring que este repository
 * gere a entidade `Combustivel`, e que o tipo da sua chave primária (`@Id`) é `Integer`.
 *
 * Vantagens desta abordagem sobre o DAO manual:
 * - Redução Drástica de Código: Eliminamos centenas de linhas de código JDBC repetitivo.
 * - Segurança e Otimização: O Spring Data JPA gera implementações otimizadas e seguras.
 * - Produtividade: O desenvolvimento da camada de dados torna-se quase instantâneo.
 */
@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Integer> {
    // Não é necessário escrever nenhum método aqui por enquanto.
    // O Spring Data JPA já nos fornece todo o CRUD básico.
}

