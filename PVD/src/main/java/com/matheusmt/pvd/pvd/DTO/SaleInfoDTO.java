package com.matheusmt.pvd.pvd.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfoDTO {
    private String username;
    private String date;
    private List<ProductDTOInfo> products;
}
