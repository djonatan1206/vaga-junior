package com.djonatan.abastecimento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a entidade 'Usuario', mapeada para a tabela 'usuario' no banco de dados.
 * Esta classe define a estrutura de um utilizador, incluindo as suas credenciais e o seu
 * papel (role) no sistema, que determina as suas permissões.
 */
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    /**
     * Enum para representar os papéis (roles) de utilizador de forma segura e controlada.
     * Usar um enum previne erros de digitação e limita as opções a 'ADMIN' e 'OPERADOR'.
     */
    public enum Papel {
        ADMIN,
        OPERADOR
    }

    /**
     * Identificador único do utilizador.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nome de utilizador para login.
     * A restrição `unique = true` garante que não podem existir dois utilizadores com o mesmo nome.
     */
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    /**
     * Palavra-passe do utilizador.
     * Num sistema de produção, este campo armazenaria um hash da palavra-passe, não o texto simples.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * O papel do utilizador no sistema, que define o seu nível de acesso.
     * - @Enumerated(EnumType.STRING): Instrui o JPA a persistir o nome do enum ("ADMIN", "OPERADOR")
     * como uma String no banco, o que é uma prática mais robusta e legível.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Papel papel;
}
