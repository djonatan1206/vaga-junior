package com.djonatan.abastecimento.controller;

import com.djonatan.abastecimento.dto.AbastecimentoRequest;
import com.djonatan.abastecimento.model.Abastecimento;
import com.djonatan.abastecimento.service.AbastecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para a entidade Abastecimento.
 * Expõe os endpoints para registar e consultar os abastecimentos.
 */
@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    @Autowired
    private AbastecimentoService abastecimentoService;

    @GetMapping
    public List<Abastecimento> listarTodos() {
        return abastecimentoService.listarTodos();
    }

    /**
     * Endpoint para registar um novo abastecimento.
     * Este método é flexível e utiliza o DTO AbastecimentoRequest.
     * Ele verifica se o cliente enviou 'litros' ou 'valor' e chama o método de serviço apropriado.
     *
     * @param request O DTO contendo o bombaId e os litros ou o valor.
     * @return O objeto Abastecimento completo que foi criado.
     */
    @PostMapping
    public Abastecimento registar(@RequestBody AbastecimentoRequest request) {
        if (request.getLitros() != null) {
            return abastecimentoService.registarPorLitros(request.getBombaId(), request.getLitros());
        } else if (request.getValor() != null) {
            return abastecimentoService.registarPorValor(request.getBombaId(), request.getValor());
        } else {
            // Lança uma exceção se nem litros nem valor forem fornecidos.
            throw new IllegalArgumentException("É necessário fornecer a quantidade de litros ou o valor total.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        // A lógica de permissão (se o utilizador é admin e a palavra-passe está correta)
        // seria implementada aqui ou no serviço antes de chamar o repository.
        abastecimentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
