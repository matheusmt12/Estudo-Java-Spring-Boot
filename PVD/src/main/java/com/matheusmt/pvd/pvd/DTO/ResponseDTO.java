package com.matheusmt.pvd.pvd.DTO;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO<T> {

    @Getter
    private List<String> menssages;

    public ResponseDTO(List<String> menssages) {
        this.menssages = menssages;
    }

    public ResponseDTO(String menssages) {
        this.menssages = Arrays.asList(menssages);

    }
}
