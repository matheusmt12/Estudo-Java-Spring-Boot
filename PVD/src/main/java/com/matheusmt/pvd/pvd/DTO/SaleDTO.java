package com.matheusmt.pvd.pvd.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long userid;
    private List<ProductDTO> item;
}
