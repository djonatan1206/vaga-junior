package com.djonatan.abastecimento.controller;

import com.djonatan.abastecimento.model.Bomba;
import com.djonatan.abastecimento.service.BombaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para a entidade Bomba.
 * Expõe os endpoints para o CRUD completo de bombas de combustível.
 */
@RestController
@RequestMapping("/api/bombas")
public class BombaController {

    @Autowired
    private BombaService bombaService;

    @GetMapping
    public List<Bomba> listarTodas() {
        return bombaService.listarTodas();
    }

    /**
     * Endpoint para criar uma nova bomba.
     * O corpo do pedido deve conter um JSON com "nome" e um objeto "combustivel"
     * que precisa de ter, no mínimo, o "id" do combustível a ser associado.
     * Ex: { "nome": "Bomba Nova", "combustivel": { "id": 1 } }
     */
    @PostMapping
    public Bomba criar(@RequestBody Bomba bomba) {
        return bombaService.salvar(bomba);
    }

    @PutMapping("/{id}")
    public Bomba atualizar(@PathVariable Integer id, @RequestBody Bomba bomba) {
        bomba.setId(id);
        return bombaService.salvar(bomba);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        bombaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
