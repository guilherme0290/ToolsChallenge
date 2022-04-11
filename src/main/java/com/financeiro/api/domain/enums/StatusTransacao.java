package com.financeiro.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum StatusTransacao {
    AUTORIZADO(0,"Autorizado"),
    NEGADO(1,"negado"),
    CANCELADO(2,"cancelado");

    private Integer codigo;
    private String descricao;
}
