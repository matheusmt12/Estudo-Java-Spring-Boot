package com.matheusmt.pvd.pvd.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTOInfo {

    private String description;
    private int quantity;
    private BigDecimal price;
}
