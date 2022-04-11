package com.financeiro.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.controller.TransacaoController;
import com.financeiro.api.domain.Transacao;
import com.financeiro.api.domain.TransacaoDescricao;
import com.financeiro.api.domain.dtos.DescricaoDTO;
import com.financeiro.api.domain.dtos.FormaPagamentoDTO;
import com.financeiro.api.domain.dtos.TransDTO;
import com.financeiro.api.domain.dtos.TransacaoDTO;
import com.financeiro.api.domain.enums.StatusTransacao;
import com.financeiro.api.domain.enums.TipoPagamento;
import com.financeiro.api.repository.TransacaoDescricaoRepository;
import com.financeiro.api.services.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


//@WebMvcTest(TransacaoController.class)
//@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TransacaoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @Autowired
    TransacaoService transacaoService;

    @Test
    public void createTransacao_sucess() throws Exception{

        DescricaoDTO descricao = DescricaoDTO.builder()
                .valor(new BigDecimal(500))
                .status(StatusTransacao.AUTORIZADO)
                .nsu("13513")
                .codigoAutorizacao("53135135135")
                .dataHora(new Date(System.currentTimeMillis()))
                .build();

        FormaPagamentoDTO formaPagamentoDTO = FormaPagamentoDTO.builder()
                        .parcelas(1)
                        .tipo(TipoPagamento.AVISTA)
                        .build();
        TransDTO transDTO = TransDTO.builder()
                .cartao("1351351")
                .descricao(descricao)
                .formaPagamento(formaPagamentoDTO)
                .build();

        TransacaoDTO transacaoDTO = TransacaoDTO.builder()
                        .transacao(transDTO)
                        .build();
        TransacaoService service = Mockito.mock(TransacaoService.class);

        Transacao transacao =service.create(transacaoDTO);
        Mockito.when(transacao).thenReturn(transacao);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(transacaoDTO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void estornoTransacao_sucess() throws Exception{

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/transacao/estorno/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

    }





}
