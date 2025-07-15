package com.djonatan.abastecimento.repository;

import com.djonatan.abastecimento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository para a entidade Utilizador.
 *
 * Demonstração de "Query Methods":
 * O Spring Data JPA tem um poderoso mecanismo de criação de consultas a partir do nome do método.
 * Ao declarar um método como `findByUsername`, o Spring entende que deve gerar uma consulta
 * que procura na tabela 'usuario' por uma linha onde a coluna 'username' corresponda ao
 * parâmetro fornecido.
 *
 * Uso de `Optional<T>`:
 * Em vez de retornar o objeto diretamente ou `null` (como fazíamos no DAO), é uma boa prática
 * moderna retornar um `Optional<Usuario>`. O Optional é um "contentor" que pode ou não
 * conter um valor. Isto força quem chama o método a lidar explicitamente com o caso em que
 * o utilizador não é encontrado, evitando os temidos `NullPointerException`.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Procura um utilizador pelo seu nome de utilizador (username).
     * Este método é a base para o nosso sistema de autenticação.
     *
     * @param username O nome de utilizador a ser procurado.
     * @return um Optional contendo o Utilizador se encontrado, ou um Optional vazio caso contrário.
     */
    Optional<Usuario> findByUsername(String username);
}
