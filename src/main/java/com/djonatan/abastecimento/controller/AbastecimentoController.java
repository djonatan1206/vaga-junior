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
 * Esta classe é a porta de entrada para todas as operações HTTP relacionadas a abastecimentos.
 * A sua responsabilidade é receber os pedidos, delegar a lógica de negócio para a camada de
 * Serviço (`AbastecimentoService`) e formatar a resposta para o cliente.
 */
@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

    @Autowired
    private AbastecimentoService abastecimentoService;

    /**
     * Endpoint para listar o histórico de todos os abastecimentos.
     * Mapeado para o método HTTP GET em "/api/abastecimentos".
     *
     * @return Uma lista de todos os abastecimentos, serializada automaticamente para JSON.
     */
    @GetMapping
    public List<Abastecimento> listarTodos() {
        return abastecimentoService.listarTodos();
    }

    /**
     * Endpoint para registar um novo abastecimento.
     * Mapeado para o método HTTP POST em "/api/abastecimentos".
     * Este método demonstra o uso de um DTO (`AbastecimentoRequest`) para receber os dados,
     * o que torna a API mais flexível e segura.
     *
     * @param request O DTO contendo o ID da bomba e os litros ou o valor do abastecimento.
     * @return O objeto Abastecimento completo que foi criado e persistido.
     */
    @PostMapping
    public Abastecimento registar(@RequestBody AbastecimentoRequest request) {
        // Delega para o serviço a decisão de qual lógica de negócio aplicar.
        if (request.getLitros() != null) {
            return abastecimentoService.registarPorLitros(request.getBombaId(), request.getLitros());
        } else if (request.getValor() != null) {
            return abastecimentoService.registarPorValor(request.getBombaId(), request.getValor());
        } else {
            // Lança uma exceção se dados essenciais não forem fornecidos,
            // resultando numa resposta HTTP 400 (Bad Request) para o cliente.
            throw new IllegalArgumentException("É necessário fornecer a quantidade de litros ou o valor total.");
        }
    }

    /**
     * Endpoint para remover um abastecimento pelo seu ID.
     * Mapeado para o método HTTP DELETE em "/api/abastecimentos/{id}".
     * A segurança desta operação (verificar se o utilizador é admin) seria implementada
     * numa camada de segurança (ex: Spring Security) ou no próprio serviço.
     *
     * @param id O ID do abastecimento a ser removido.
     * @return Um ResponseEntity com status HTTP 204 (No Content), que é a prática
     * padrão para operações de DELETE bem-sucedidas.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        abastecimentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
