package com.djonatan.abastecimento.controller;

import com.djonatan.abastecimento.dto.LoginRequest;
import com.djonatan.abastecimento.model.Usuario;
import com.djonatan.abastecimento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para autenticação e gestão de utilizadores.
 * Por convenção, as rotas de autenticação são separadas (ex: /api/auth) das rotas de
 * gestão de recursos (ex: /api/usuarios), para uma melhor organização da API.
 */
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint de login para autenticação de utilizadores.
     * Mapeado para o método HTTP POST em "/api/auth/login".
     *
     * @param loginRequest DTO com as credenciais (username e password). O uso de um DTO
     * aqui é uma boa prática para não expor a entidade Utilizador diretamente.
     * @return Um ResponseEntity que encapsula a resposta HTTP.
     * - Em caso de sucesso: Retorna o objeto Utilizador (com a palavra-passe removida) e o status 200 OK.
     * - Em caso de falha: Retorna um corpo vazio e o status 401 UNAUTHORIZED.
     */
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {
        // Delega a lógica de autenticação para a camada de serviço.
        return usuarioService.autenticar(loginRequest.getUsername(), loginRequest.getPassword())
                .map(usuario -> {
                    // Boa prática de segurança: nunca retornar a palavra-passe na resposta da API.
                    // A melhor abordagem seria usar um DTO de resposta (ex: UsuarioResponseDTO).
                    // Para este desafio, definir a palavra-passe como nula é uma medida de segurança simples e eficaz.
                    usuario.setPassword(null);
                    return ResponseEntity.ok(usuario);
                })
                // Se o Optional retornado pelo serviço estiver vazio, constrói uma resposta 401.
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Endpoints para registar novos utilizadores (acessível apenas por admins) seriam adicionados
    // aqui ou, preferencialmente, num novo Controller (ex: AdminController) com um
    // RequestMapping como "/api/admin/usuarios".
}
