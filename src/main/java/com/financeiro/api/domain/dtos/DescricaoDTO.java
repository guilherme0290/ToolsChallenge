package com.financeiro.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financeiro.api.domain.enums.StatusTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DescricaoDTO {

    private BigDecimal valor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    @Enumerated
    private StatusTransacao status;




}
