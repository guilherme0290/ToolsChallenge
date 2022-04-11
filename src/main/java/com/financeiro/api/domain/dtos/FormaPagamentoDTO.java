package com.financeiro.api.domain.dtos;

import com.financeiro.api.domain.enums.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagamentoDTO {
    private TipoPagamento tipo;
    @NotNull(message = "É obrigatorio informa a parcela da forma de pagamento")
    private Integer parcelas;
}
