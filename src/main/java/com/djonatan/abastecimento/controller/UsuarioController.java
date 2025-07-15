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
 * Por convenção, as rotas de autenticação são separadas (ex: /api/auth).
 */
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint de login.
     * Recebe um nome de utilizador e uma palavra-passe e tenta autenticar.
     *
     * @param loginRequest DTO com as credenciais.
     * @return Um ResponseEntity contendo o objeto Utilizador (sem a palavra-passe, num caso real)
     * e o status 200 OK se o login for bem-sucedido, ou o status 401 UNAUTHORIZED se falhar.
     */
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.autenticar(loginRequest.getUsername(), loginRequest.getPassword())
                .map(usuario -> {
                    // Num sistema real, nunca retornaríamos a palavra-passe.
                    // Criaríamos um DTO de resposta (ex: UsuarioResponseDTO) sem o campo da palavra-passe.
                    usuario.setPassword(null); // Medida de segurança simples para o desafio.
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Endpoints para registar novos utilizadores (acessível apenas por admins)
    // seriam adicionados aqui, provavelmente num @RequestMapping("/api/usuarios").
}
