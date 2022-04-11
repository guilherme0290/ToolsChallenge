package com.financeiro.api.controller;

import com.financeiro.api.domain.Transacao;
import com.financeiro.api.domain.TransacaoDescricao;
import com.financeiro.api.domain.TransacaoPagamento;
import com.financeiro.api.domain.dtos.DescricaoDTO;
import com.financeiro.api.domain.dtos.FormaPagamentoDTO;
import com.financeiro.api.domain.dtos.TransDTO;
import com.financeiro.api.domain.dtos.TransacaoDTO;
import com.financeiro.api.domain.enums.StatusTransacao;
import com.financeiro.api.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TransacaoDTO> create( @RequestBody @Valid TransacaoDTO objDTO){
        Transacao obj = transacaoService.create(objDTO);
     //   return convertToTransDTO(obj);
        return ResponseEntity.ok().body(convertToTransDTO(obj));
    }

    @PutMapping(value = "/estorno/{id}")
    public TransacaoDTO estorno(@PathVariable Integer id){
       return transacaoService.estornoTransacao(id)
               .map(p -> convertToTransDTO(p))
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transação não encontrada: "+id));
    }

    @GetMapping(value = "/{id}")
    public TransacaoDTO findById(@PathVariable Integer id){
        return transacaoService.obterTransacaoCompleto(id)
                .map( p -> convertToTransDTO(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transação não encontrada: "+id));
    }

    @GetMapping
    public List<TransacaoDTO> findAll(){
        List<Transacao> list = transacaoService.findAll();

        List<TransacaoDTO> listDTO = list.stream().map(obj -> new TransacaoDTO(convertToTranscaoDTO(obj))).collect(Collectors.toList());
        return listDTO;
    }


    private DescricaoDTO convertToDescricaoDTO(TransacaoDescricao obj){
        return DescricaoDTO
                .builder()
                .dataHora(obj.getDataHora())
                .estabelecimento(obj.getEstabelecimento())
                .valor(obj.getValor())
                .nsu(obj.getNsu())
                .codigoAutorizacao(obj.getCodigoAutorizacao())
                .status(obj.getStatus())
                .build();
    }
    private TransDTO convertToTranscaoDTO(Transacao obj){
        return TransDTO
                .builder()
                .cartao(obj.getCartao())
                .id(obj.getId())
                .descricao(convertToDescricaoDTO(obj.getDescricao()))
                .formaPagamento(convertToFormaPag(obj.getPagamento()))
                .build();
    }

    private TransacaoDTO convertToTransDTO(Transacao obj){
        return TransacaoDTO
                .builder()
                .transacao(convertToTranscaoDTO(obj))
                .build();
    }

    private FormaPagamentoDTO convertToFormaPag(TransacaoPagamento obj){
        return FormaPagamentoDTO
                .builder()
                .parcelas(obj.getParcelas())
                .tipo(obj.getTipo())
                .build();
    }




}
