package com.djonatan.abastecimento.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AbastecimentoRequest {
    private int bombaId;
    private BigDecimal litros;
    private BigDecimal valor;
}
