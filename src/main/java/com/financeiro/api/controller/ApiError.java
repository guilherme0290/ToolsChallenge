package com.financeiro.api.controller;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {

    @Getter
    private List<String> erros;

    public ApiError(String mensagemErros){
        this.erros = Arrays.asList(mensagemErros);
    }

    public ApiError(List<String> erros) {
        this.erros = erros;
    }



}
