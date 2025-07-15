package com.djonatan.abastecimento.controller;

import com.djonatan.abastecimento.model.Combustivel;
import com.djonatan.abastecimento.service.CombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para a entidade Combustivel.
 * Esta classe expõe os endpoints (URLs) que permitem a um cliente externo (como uma aplicação web
 * ou móvel) interagir com os dados de combustíveis através do protocolo HTTP.
 *
 * Anotações Chave:
 * - @RestController: Combina @Controller e @ResponseBody. Indica que esta classe é um controlador REST
 * e que os valores retornados pelos seus métodos serão automaticamente serializados para JSON
 * e escritos no corpo da resposta HTTP.
 *
 * - @RequestMapping("/api/combustiveis"): Define um prefixo de URL base para todos os endpoints
 * definidos nesta classe. Todas as rotas aqui começarão com "/api/combustiveis".
 *
 * - @Autowired: Realiza a injeção de dependência do nosso CombustivelService. O Spring
 * encarrega-se de fornecer a instância correta.
 */
@RestController
@RequestMapping("/api/combustiveis")
public class CombustivelController {

    @Autowired
    private CombustivelService combustivelService;

    /**
     * Endpoint para listar todos os combustíveis.
     * Mapeado para o método HTTP GET na URL base "/api/combustiveis".
     * @return Uma lista de todos os combustíveis em formato JSON.
     */
    @GetMapping
    public List<Combustivel> listarTodos() {
        return combustivelService.listarTodos();
    }

    /**
     * Endpoint para criar um novo combustível.
     * Mapeado para o método HTTP POST na URL base "/api/combustiveis".
     *
     * @param combustivel O objeto Combustivel enviado no corpo do pedido (request body) em formato JSON.
     * A anotação @RequestBody encarrega-se de desserializar o JSON para o objeto Java.
     * @return Um ResponseEntity contendo o combustível criado e o status HTTP 201 (Created).
     * Usar ResponseEntity dá-nos controlo total sobre a resposta HTTP.
     */
    @PostMapping
    public ResponseEntity<Combustivel> criar(@RequestBody Combustivel combustivel) {
        Combustivel combustivelSalvo = combustivelService.salvar(combustivel);
        return new ResponseEntity<>(combustivelSalvo, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar um combustível existente.
     * Mapeado para o método HTTP PUT na URL "/api/combustiveis/{id}".
     *
     * @param id O ID do combustível a ser atualizado, capturado da URL pela anotação @PathVariable.
     * @param combustivel O objeto Combustivel com os novos dados, vindo do corpo do pedido.
     * @return O objeto Combustivel atualizado.
     */
    @PutMapping("/{id}")
    public Combustivel atualizar(@PathVariable Integer id, @RequestBody Combustivel combustivel) {
        // Garante que o ID do objeto a ser salvo é o mesmo da URL, para consistência.
        combustivel.setId(id);
        return combustivelService.salvar(combustivel);
    }

    /**
     * Endpoint para remover um combustível.
     * Mapeado para o método HTTP DELETE na URL "/api/combustiveis/{id}".
     *
     * @param id O ID do combustível a ser removido, capturado da URL.
     * @return Um ResponseEntity com status HTTP 204 (No Content), indicando sucesso na remoção
     * sem necessidade de retornar um corpo na resposta. Esta é uma prática padrão em APIs REST.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        combustivelService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
