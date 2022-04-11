package com.financeiro.api.repository;

import com.financeiro.api.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Integer> {

}
