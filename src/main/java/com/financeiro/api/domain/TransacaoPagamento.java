package com.financeiro.api.domain;

import com.financeiro.api.domain.enums.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransacaoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated
    @NotNull(message = "O campo TIPO não pode ser nulo")
    private TipoPagamento tipo;
    @NotNull(message = "O campo PARCELAS não pode ser nulo")
    private Integer parcelas;

}
