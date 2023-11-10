package com.matheusmt.pvd.pvd.DTO;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO<T> {

    @Getter
    private List<String> menssages;
    @Getter
    private T data;

    public ResponseDTO(List<String> menssages, T data) {
        this.menssages = menssages;
        this.data = data;
    }

    public ResponseDTO(String menssages, T dada) {
        this.menssages = Arrays.asList(menssages);
        this.data = dada;
    }
}
