package com.djonatan.abastecimento.service;

import com.djonatan.abastecimento.model.Bomba;
import com.djonatan.abastecimento.model.Combustivel;
import com.djonatan.abastecimento.repository.BombaRepository;
import com.djonatan.abastecimento.repository.CombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de Serviço para a entidade Bomba.
 *
 * Orquestração de Repositories:
 * Este serviço demonstra como uma camada de serviço pode orquestrar operações que envolvem
 * mais de um repository. Para salvar uma bomba, ele precisa de interagir tanto com o
 * `BombaRepository` como com o `CombustivelRepository` para garantir a integridade
 * do relacionamento entre as entidades.
 */
@Service
public class BombaService {

    @Autowired
    private BombaRepository bombaRepository;

    // Injetamos também o CombustivelRepository para validar a existência do combustível.
    @Autowired
    private CombustivelRepository combustivelRepository;

    /**
     * Retorna uma lista de todas as bombas registadas.
     * @return a lista de bombas.
     */
    public List<Bomba> listarTodas() {
        return bombaRepository.findAll();
    }

    /**
     * Salva uma nova bomba ou atualiza uma existente.
     *
     * Lógica de Negócio:
     * Antes de salvar, este método valida se o ID do combustível fornecido corresponde a um
     * combustível real no banco de dados. Isto previne a criação de dados inconsistentes
     * (uma bomba associada a um combustível que não existe).
     *
     * @param bomba O objeto Bomba a ser salvo. O seu atributo 'combustivel' deve conter
     * pelo menos o ID do combustível desejado.
     * @return O objeto Bomba salvo.
     * @throws RuntimeException se o combustível associado não for encontrado.
     */
    public Bomba salvar(Bomba bomba) {
        // Extrai o ID do combustível do objeto bomba recebido.
        int combustivelId = bomba.getCombustivel().getId();

        // Procura o combustível no banco de dados para garantir que ele existe.
        Combustivel combustivel = combustivelRepository.findById(combustivelId)
                .orElseThrow(() -> new RuntimeException("Combustível não encontrado com o ID: " + combustivelId));

        // Associa a entidade Combustivel completa (e não apenas o ID) ao objeto Bomba.
        bomba.setCombustivel(combustivel);

        // Salva a bomba, agora com a referência de combustível validada e completa.
        return bombaRepository.save(bomba);
    }

    /**
     * Remove uma bomba pelo seu ID.
     * @param id O ID da bomba a ser removida.
     */
    public void remover(Integer id) {
        // A lógica de verificação de dependências (se a bomba tem abastecimentos)
        // será tratada pelo Controller através da captura de exceções.
        bombaRepository.deleteById(id);
    }
}
