package com.djonatan.abastecimento.service;

import com.djonatan.abastecimento.model.Usuario;
import com.djonatan.abastecimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Camada de Serviço para a entidade Utilizador.
 * Centraliza a lógica de negócio relacionada à autenticação e gestão de utilizadores.
 *
 * Responsabilidades:
 * - Validar credenciais de login.
 * - Orquestrar o registo de novos utilizadores, aplicando regras de negócio.
 * - Interagir com o UsuarioRepository para persistir e recuperar dados de utilizadores.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Autentica um utilizador com base no nome de utilizador e palavra-passe.
     *
     * @param username O nome de utilizador fornecido.
     * @param password A palavra-passe fornecida.
     * @return um Optional contendo o objeto Utilizador se a autenticação for bem-sucedida,
     * ou um Optional vazio caso contrário.
     */
    public Optional<Usuario> autenticar(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            return usuarioOpt;
        }

        return Optional.empty();
    }

    /**
     * Regista um novo utilizador no sistema.
     *
     * @param username O nome do novo utilizador.
     * @param password A palavra-passe para o novo utilizador.
     * @param papel O papel (ADMIN ou OPERADOR) do novo utilizador.
     * @return O objeto Utilizador que foi criado e salvo.
     * @throws RuntimeException se o nome de utilizador já existir.
     */
    public Usuario registar(String username, String password, Usuario.Papel papel) {
        // Regra de negócio: impede o registo de um utilizador se o nome já estiver em uso.
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("O nome de utilizador '" + username + "' já está em uso.");
        }

        Usuario novoUtilizador = new Usuario();
        novoUtilizador.setUsername(username);
        novoUtilizador.setPassword(password); // Num sistema real, a palavra-passe seria criptografada aqui.
        novoUtilizador.setPapel(papel);

        return usuarioRepository.save(novoUtilizador);
    }
}
