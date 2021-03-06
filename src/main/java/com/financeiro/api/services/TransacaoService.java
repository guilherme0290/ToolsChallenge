package com.financeiro.api.services;

import com.financeiro.api.controller.TransacaoController;
import com.financeiro.api.domain.Transacao;
import com.financeiro.api.domain.TransacaoDescricao;
import com.financeiro.api.domain.TransacaoPagamento;
import com.financeiro.api.domain.dtos.TransacaoDTO;
import com.financeiro.api.domain.enums.StatusTransacao;
import com.financeiro.api.domain.enums.TipoPagamento;
import com.financeiro.api.repository.TransacaoDescricaoRepository;
import com.financeiro.api.repository.TransacaoPagamentoRepository;
import com.financeiro.api.repository.TransacaoRepository;
import com.financeiro.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private TransacaoDescricaoRepository transDescricaoRep;

    @Autowired
    private TransacaoPagamentoRepository transPagRep;

    public Transacao create(TransacaoDTO obj){
        return transacaoRepository.save(newTransacao(obj));
    }

    public Optional<Transacao> obterTransacaoCompleto(Integer id){
        return transacaoRepository.findById(id);
    }

    public List<Transacao> findAll(){
        List<Transacao> list = transacaoRepository.findAll();
        return list;
    }


//    @GetMapping
//    public ResponseEntity<List<ClienteDTO>> findAll(){
//        List<Cliente> list = service.findAll();
//        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(listDTO);
//    }

    public Optional<Transacao> estornoTransacao(Integer id){
        Optional<Transacao>  tras = transacaoRepository.findById(id);
        if (tras.isPresent()){
            Optional<TransacaoDescricao> transacaoDescricao = transDescricaoRep.findById(tras.get().getDescricao().getId());
            if(transacaoDescricao.isPresent()){
                transacaoDescricao.get().setStatus(StatusTransacao.CANCELADO);
                transDescricaoRep.save(transacaoDescricao.get());
                return tras;
            }
        }
      throw new ObjectNotFoundException("Transa????o n??o encontrada id: " +id);
    }




//    public Transacao findById(Integer id){
//        Optional<Transacao> obj = transacaoRepository.findById(id);
//        return obj.orElseThrow(()-> new ObjectNotFoundException("Transa????o n??o encontrada id:"+id));
//    }

    private Transacao newTransacao(TransacaoDTO obj){
        if (obj.getTransacao() != null){
            TransacaoDescricao descSave = createTransacaoDescricao(obj);
            TransacaoPagamento pagSave = createTransacaoPagamento(obj);
            Transacao trans = new Transacao();
            trans.setCartao(obj.getTransacao().getCartao());
            trans.setDescricao(descSave);
            trans.setPagamento(pagSave);
            Transacao transacao = transacaoRepository.save(trans);
            return transacao;
        }else{
            throw  new ObjectNotFoundException("Objeto transacao n??o encontrado");
        }
    }

    private TransacaoDescricao createTransacaoDescricao(TransacaoDTO obj){
        TransacaoDescricao descricao = new TransacaoDescricao();
        if (obj.getTransacao() != null && obj.getTransacao().getDescricao() != null){
            descricao.setValor(obj.getTransacao().getDescricao().getValor());
            descricao.setDataHora(obj.getTransacao().getDescricao().getDataHora());
            descricao.setEstabelecimento(obj.getTransacao().getDescricao().getEstabelecimento());
            descricao.setNsu(geraNsu());
            descricao.setCodigoAutorizacao(geraCodigoAutorizador());
            descricao.setStatus(StatusTransacao.AUTORIZADO);
            TransacaoDescricao transacaoDescricao = transDescricaoRep.save(descricao);
            return transacaoDescricao;
        }else{
            throw  new ObjectNotFoundException("Objeto descricao n??o encontrado");
        }
    }

    private TransacaoPagamento createTransacaoPagamento(TransacaoDTO obj){
        if (obj.getTransacao() != null && obj.getTransacao().getFormaPagamento() != null){
            TransacaoPagamento transPag = new TransacaoPagamento();
            transPag.setParcelas(obj.getTransacao().getFormaPagamento().getParcelas());
            TipoPagamento tp = TipoPagamento.fromValue(obj.getTransacao().getFormaPagamento().getTipo().getDescricao());
            transPag.setTipo(tp);
            TransacaoPagamento transacaoPagamento = transPagRep.save(transPag);
            return transacaoPagamento;
        }else{
            throw  new ObjectNotFoundException("Objeto forma de pagamento n??o encontrado");
        }
    }

    private  String geraNsu(){
        Faker faker = new Faker();
        return faker.number().digits(5);
    }

    private String geraCodigoAutorizador(){
        Faker faker = new Faker();
        return faker.number().digits(10);
    }

}
