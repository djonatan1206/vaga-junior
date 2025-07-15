package com.djonatan.abastecimento.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para receber os dados de um novo registo de abastecimento.
 *
 * Propósito e Design:
 * Esta classe serve como um "contrato" de dados para o endpoint de criação de abastecimentos.
 * Em vez de expor a entidade `Abastecimento` completa, este DTO permite que o cliente envie
 * apenas as informações estritamente necessárias para iniciar a operação.
 *
 * A principal vantagem aqui é a flexibilidade: o cliente pode fornecer a quantidade de
 * `litros` OU o `valor` total. A lógica para decidir qual cálculo realizar (valor a partir
 * dos litros ou litros a partir do valor) é então tratada na camada de Serviço,
 * mantendo o Controller limpo e o modelo de dados desacoplado da interface da API.
 */
@Data
public class AbastecimentoRequest {

    /**
     * O ID da bomba onde o abastecimento foi realizado.
     */
    private int bombaId;

    /**
     * A quantidade de litros a ser abastecida.
     * Este campo é opcional; o cliente pode fornecer este ou o campo 'valor'.
     */
    private BigDecimal litros;

    /**
     * O valor total em Reais a ser abastecido.
     * Este campo é opcional; o cliente pode fornecer este ou o campo 'litros'.
     */
    private BigDecimal valor;
}
