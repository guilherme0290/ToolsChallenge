package com.financeiro.api.repository;

import com.financeiro.api.domain.TransacaoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoPagamentoRepository extends JpaRepository<TransacaoPagamento,Integer> {
}
