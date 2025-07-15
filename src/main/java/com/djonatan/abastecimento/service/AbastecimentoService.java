package com.djonatan.abastecimento.service;

import com.djonatan.abastecimento.model.Abastecimento;
import com.djonatan.abastecimento.model.Bomba;
import com.djonatan.abastecimento.repository.AbastecimentoRepository;
import com.djonatan.abastecimento.repository.BombaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Camada de Serviço para a entidade Abastecimento.
 * Esta classe contém a lógica de negócio principal relacionada aos abastecimentos.
 *
 * Anotações Chave:
 * - @Service: Marca esta classe como um componente de serviço no contexto do Spring.
 * Isto permite que o Spring a detete e a injete onde for necessário.
 * - @Autowired: Utilizada para a Injeção de Dependência. O Spring irá automaticamente
 * fornecer as instâncias dos Repositories necessários para esta classe.
 *
 * Responsabilidades:
 * - Mediar a comunicação entre a camada de Controller e a camada de Repository.
 * - Encapsular regras de negócio complexas, como o cálculo de valores e litros.
 * - Garantir a integridade e a consistência dos dados antes da persistência.
 */
@Service
public class AbastecimentoService {

    // Injeção de dependência dos repositories necessários.
    @Autowired
    private AbastecimentoRepository abastecimentoRepository;

    @Autowired
    private BombaRepository bombaRepository;

    /**
     * Lista todos os abastecimentos registados, ordenados do mais recente para o mais antigo.
     * @return Uma lista de objetos Abastecimento.
     */
    public List<Abastecimento> listarTodos() {
        // Usa o método personalizado do repository para obter os dados já ordenados.
        return abastecimentoRepository.findAllByOrderByIdDesc();
    }

    /**
     * Regista um novo abastecimento com base nos litros fornecidos.
     * A lógica de negócio para calcular o valor total está encapsulada aqui.
     *
     * @param bombaId O ID da bomba utilizada.
     * @param litros A quantidade de litros abastecida.
     * @return O objeto Abastecimento que foi criado e salvo.
     */
    public Abastecimento registarPorLitros(int bombaId, BigDecimal litros) {
        // Busca a bomba no banco de dados para garantir que ela existe.
        Bomba bomba = bombaRepository.findById(bombaId)
                .orElseThrow(() -> new RuntimeException("Bomba não encontrada com o ID: " + bombaId));

        // Calcula o valor total
        BigDecimal precoPorLitro = bomba.getCombustivel().getPrecoPorLitro();
        BigDecimal valorTotal = litros.multiply(precoPorLitro).setScale(2, RoundingMode.HALF_UP);

        // Cria a nova entidade Abastecimento
        Abastecimento novoAbastecimento = new Abastecimento();
        novoAbastecimento.setBomba(bomba);
        novoAbastecimento.setLitros(litros);
        novoAbastecimento.setValorTotal(valorTotal);
        novoAbastecimento.setData(LocalDateTime.now());

        // Salva a entidade no banco de dados usando o repository.
        return abastecimentoRepository.save(novoAbastecimento);
    }

    /**
     * Regista um novo abastecimento com base no valor total fornecido.
     * A lógica de negócio para calcular os litros está encapsulada aqui.
     *
     * @param bombaId O ID da bomba utilizada.
     * @param valorTotal O valor em Reais a ser abastecido.
     * @return O objeto Abastecimento que foi criado e salvo.
     */
    public Abastecimento registarPorValor(int bombaId, BigDecimal valorTotal) {
        Bomba bomba = bombaRepository.findById(bombaId)
                .orElseThrow(() -> new RuntimeException("Bomba não encontrada com o ID: " + bombaId));

        // Calcula os litros
        BigDecimal precoPorLitro = bomba.getCombustivel().getPrecoPorLitro();
        if (precoPorLitro.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço do combustível inválido para cálculo.");
        }
        BigDecimal litros = valorTotal.divide(precoPorLitro, 3, RoundingMode.HALF_UP);

        // Cria a nova entidade Abastecimento
        Abastecimento novoAbastecimento = new Abastecimento();
        novoAbastecimento.setBomba(bomba);
        novoAbastecimento.setLitros(litros);
        novoAbastecimento.setValorTotal(valorTotal.setScale(2, RoundingMode.HALF_UP));
        novoAbastecimento.setData(LocalDateTime.now());

        return abastecimentoRepository.save(novoAbastecimento);
    }

    // Métodos para atualizar e remover podem ser adicionados aqui,
    // incluindo a lógica de verificação de permissão do utilizador.
    public void remover(int id) {
        abastecimentoRepository.deleteById(id);
    }
}
