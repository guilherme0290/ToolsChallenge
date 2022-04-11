package com.financeiro.api.domain;


import com.financeiro.api.domain.dtos.DescricaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O campo CARTAO não pode ser vazio")
    private String cartao;

    @ManyToOne
    @JoinColumn(name = "descricao_id")
    @NotNull(message = "É obrigatório inserir a descricao da transacao")
    private TransacaoDescricao descricao;

    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    @NotNull(message = "É obrigatório inserir a forma de pagamento da transacao")
    private TransacaoPagamento pagamento;









}
