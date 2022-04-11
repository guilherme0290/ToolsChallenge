package com.financeiro.api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransDTO {
    private Integer id;
    private String cartao;
    @NotBlank(message = "É obrigatorio inserir a descricao da transação")
    private DescricaoDTO descricao;
    @NotBlank(message = "É obrigatorio inserir a forma de pagamento da transação")
    private FormaPagamentoDTO formaPagamento;


}
