package com.djonatan.abastecimento.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) para encapsular as credenciais de login.
 *
 * Propósito e Design:
 * Esta classe tem o objetivo simples e claro de transportar o nome de utilizador e a palavra-passe
 * do cliente para o endpoint de autenticação.
 *
 * Vantagens de usar um DTO para login:
 * 1.  **Clareza:** Deixa explícito quais dados são esperados no corpo do pedido de login.
 * 2.  **Segurança:** Evita a necessidade de passar credenciais como parâmetros de URL,
 * que podem ser registados em logs de servidor.
 * 3.  **Flexibilidade:** Se no futuro o login precisar de mais informações (ex: um código de
 * autenticação de dois fatores), apenas este DTO precisa de ser modificado.
 */
@Data
public class LoginRequest {

    /**
     * O nome de utilizador para a autenticação.
     */
    private String username;

    /**
     * A palavra-passe para a autenticação.
     */
    private String password;
}
