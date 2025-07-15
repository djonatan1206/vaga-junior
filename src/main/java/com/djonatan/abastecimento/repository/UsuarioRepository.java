package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository para a entidade Utilizador.
 *
 * Demonstração de "Query Methods" e Boas Práticas:
 * 1.  **Query Methods:** O Spring Data JPA cria consultas a partir do nome do método. `findByUsername`
 * gera uma consulta `WHERE username = ?`, eliminando a necessidade de escrever a query manualmente.
 *
 * 2.  **Uso de `Optional<T>`:** Em vez de retornar o objeto diretamente ou `null` (o que pode levar
 * a `NullPointerException`), o método retorna um `Optional<Usuario>`. O Optional é um
 * "contentor" que pode ou não conter um valor. Esta é uma prática moderna de programação
 * em Java que força quem chama o método a lidar explicitamente com o caso em que o
 * utilizador não é encontrado, resultando num código mais seguro e robusto.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Procura um utilizador pelo seu nome de utilizador (username), que é uma coluna única.
     * Este método é a base para o nosso sistema de autenticação.
     *
     * @param username O nome de utilizador a ser procurado.
     * @return um `Optional` contendo o Utilizador se encontrado, ou um `Optional` vazio caso contrário.
     */
    Optional<Usuario> findByUsername(String username);
}
