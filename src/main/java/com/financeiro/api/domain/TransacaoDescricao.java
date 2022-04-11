package com.financeiro.api.domain;

import com.financeiro.api.domain.enums.StatusTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransacaoDescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "O campo VALOR não pode ser nulo")
    private BigDecimal valor;
    @NotNull(message = "O campo DATA/HORA não pode ser nulo")
    private Date dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;

    @NotNull(message = "O campo STATUS não pode ser nulo")
    @Enumerated
    private StatusTransacao status;


}
