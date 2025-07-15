package com.djonatan.abastecimento.service;

import com.djonatan.abastecimento.model.Combustivel;
import com.djonatan.abastecimento.repository.CombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de Serviço para a entidade Combustivel.
 *
 * Propósito Arquitetural:
 * Mesmo que muitos métodos pareçam simplesmente delegar a chamada para o Repository, esta camada
 * é crucial para uma arquitetura limpa e escalável (como o padrão hexagonal ou a arquitetura em camadas).
 * Ela serve como um "contrato" para a lógica de negócio. Se, no futuro, for necessário adicionar
 * validações, enviar notificações ou realizar qualquer outra ação ao salvar um combustível,
 * esta é a classe que será modificada, sem impactar a camada de Controller ou de Repository.
 */
@Service
public class CombustivelService {

    @Autowired
    private CombustivelRepository combustivelRepository;

    /**
     * Retorna uma lista de todos os combustíveis registados.
     * @return a lista de combustíveis.
     */
    public List<Combustivel> listarTodos() {
        return combustivelRepository.findAll();
    }

    /**
     * Salva um novo combustível ou atualiza um existente.
     * O método `save` do JpaRepository é inteligente: se o objeto não tiver ID, ele insere;
     * se tiver ID, ele atualiza o registo correspondente.
     * @param combustivel O objeto Combustivel a ser salvo.
     * @return O objeto Combustivel salvo (com o ID preenchido, se for uma nova inserção).
     */
    public Combustivel salvar(Combustivel combustivel) {
        // Futuramente, validações de negócio (ex: não permitir preço negativo) seriam adicionadas aqui.
        return combustivelRepository.save(combustivel);
    }

    /**
     * Remove um combustível pelo seu ID.
     * @param id O ID do combustível a ser removido.
     */
    public void remover(Integer id) {
        // Antes de remover, poderíamos adicionar lógicas de verificação,
        // como garantir que o combustível não está associado a nenhuma bomba.
        // No entanto, já tratamos a exceção de chave estrangeira na camada de apresentação (Main).
        combustivelRepository.deleteById(id);
    }
}
