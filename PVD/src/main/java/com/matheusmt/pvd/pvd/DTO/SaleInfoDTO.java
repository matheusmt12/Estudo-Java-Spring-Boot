package com.matheusmt.pvd.pvd.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfoDTO {
    private String username;
    private String date;
    private BigDecimal price;
    private List<ProductDTOInfo> products;
}
