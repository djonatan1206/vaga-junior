package com.djonatan.abastecimento.controller;

import com.djonatan.abastecimento.model.Bomba;
import com.djonatan.abastecimento.service.BombaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para a entidade Bomba.
 * Expõe um CRUD completo para a gestão de bombas de combustível.
 * A segurança destes endpoints (restringir o acesso a administradores) seria tipicamente
 * configurada numa camada de segurança global usando Spring Security.
 */
@RestController
@RequestMapping("/api/bombas")
public class BombaController {

    @Autowired
    private BombaService bombaService;

    /**
     * Endpoint para listar todas as bombas.
     * Mapeado para o método HTTP GET em "/api/bombas".
     * @return Uma lista de todas as bombas, incluindo os dados do combustível associado.
     */
    @GetMapping
    public List<Bomba> listarTodas() {
        return bombaService.listarTodas();
    }

    /**
     * Endpoint para criar uma nova bomba.
     * Mapeado para o método HTTP POST em "/api/bombas".
     * O corpo do pedido deve conter um JSON com "nome" e um objeto "combustivel"
     * que precisa de ter, no mínimo, o "id" do combustível a ser associado.
     * Ex: { "nome": "Bomba Nova", "combustivel": { "id": 1 } }
     *
     * @param bomba O objeto Bomba desserializado a partir do corpo do pedido JSON.
     * @return O objeto Bomba completo que foi salvo no banco de dados.
     */
    @PostMapping
    public Bomba criar(@RequestBody Bomba bomba) {
        return bombaService.salvar(bomba);
    }

    /**
     * Endpoint para atualizar uma bomba existente.
     * Mapeado para o método HTTP PUT em "/api/bombas/{id}".
     * @param id O ID da bomba a ser atualizada, extraído da URL.
     * @param bomba O objeto Bomba com os novos dados, vindo do corpo do pedido.
     * @return O objeto Bomba com os dados atualizados.
     */
    @PutMapping("/{id}")
    public Bomba atualizar(@PathVariable Integer id, @RequestBody Bomba bomba) {
        // Garante que o ID do objeto a ser salvo é o mesmo da URL, para consistência.
        bomba.setId(id);
        return bombaService.salvar(bomba);
    }

    /**
     * Endpoint para remover uma bomba pelo seu ID.
     * Mapeado para o método HTTP DELETE em "/api/bombas/{id}".
     * @param id O ID da bomba a ser removida.
     * @return Um ResponseEntity com status HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        bombaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
