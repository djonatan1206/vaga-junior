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
 * Esta classe é o coração da lógica de negócio principal da aplicação.
 *
 * Anotações Chave:
 * - @Service: Marca esta classe como um componente de serviço no contexto do Spring,
 * permitindo a injeção de dependência.
 * - @Autowired: Realiza a injeção de dependência dos Repositories, desacoplando o serviço
 * da implementação do acesso a dados.
 *
 * Responsabilidades:
 * - Mediar a comunicação entre a camada de Controller e a de Repository.
 * - Encapsular regras de negócio complexas, como o cálculo de valor a partir dos litros e vice-versa.
 * - Garantir a integridade dos dados antes da persistência (ex: verificar se a bomba existe).
 */
@Service
public class AbastecimentoService {

    @Autowired
    private AbastecimentoRepository abastecimentoRepository;

    @Autowired
    private BombaRepository bombaRepository;

    /**
     * Retorna uma lista de todos os abastecimentos, ordenados do mais recente para o mais antigo.
     * @return Uma lista de objetos Abastecimento.
     */
    public List<Abastecimento> listarTodos() {
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
        Bomba bomba = bombaRepository.findById(bombaId)
                .orElseThrow(() -> new RuntimeException("Bomba não encontrada com o ID: " + bombaId));

        BigDecimal precoPorLitro = bomba.getCombustivel().getPrecoPorLitro();
        BigDecimal valorTotal = litros.multiply(precoPorLitro).setScale(2, RoundingMode.HALF_UP);

        Abastecimento novoAbastecimento = new Abastecimento();
        novoAbastecimento.setBomba(bomba);
        novoAbastecimento.setLitros(litros);
        novoAbastecimento.setValorTotal(valorTotal);
        novoAbastecimento.setData(LocalDateTime.now());

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

        BigDecimal precoPorLitro = bomba.getCombustivel().getPrecoPorLitro();
        if (precoPorLitro.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço do combustível inválido para cálculo.");
        }
        BigDecimal litros = valorTotal.divide(precoPorLitro, 3, RoundingMode.HALF_UP);

        Abastecimento novoAbastecimento = new Abastecimento();
        novoAbastecimento.setBomba(bomba);
        novoAbastecimento.setLitros(litros);
        novoAbastecimento.setValorTotal(valorTotal.setScale(2, RoundingMode.HALF_UP));
        novoAbastecimento.setData(LocalDateTime.now());

        return abastecimentoRepository.save(novoAbastecimento);
    }

    /**
     * Remove um abastecimento do sistema.
     * A lógica de verificação de permissões (se o utilizador é admin) é tratada na camada de Controller,
     * que decide se deve ou não chamar este método.
     * @param id O ID do abastecimento a ser removido.
     */
    public void remover(int id) {
        abastecimentoRepository.deleteById(id);
    }
}
