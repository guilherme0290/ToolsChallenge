package com.financeiro.api.repository;

import com.financeiro.api.domain.TransacaoDescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransacaoDescricaoRepository extends JpaRepository<TransacaoDescricao,Integer> {

}
