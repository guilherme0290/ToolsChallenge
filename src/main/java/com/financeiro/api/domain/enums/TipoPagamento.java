package com.financeiro.api.domain.enums;

import lombok.*;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
public enum TipoPagamento {
    AVISTA(0,"AVISTA") ,PARCELADO_LOJA(1,"PARCELADO LOJA"),
    PARCELADO_EMISSOR(3,"PARCELADO EMISSOR");

    private Integer codigo;
    private String descricao;

    TipoPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoPagamento fromValue(String value) {
        for(TipoPagamento tipo : TipoPagamento.values()){
            if(tipo.getDescricao().equals(value)){
                return tipo;
            }
        }
        return null;
    }

}
