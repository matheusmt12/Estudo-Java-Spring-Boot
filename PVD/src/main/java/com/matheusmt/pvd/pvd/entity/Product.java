package com.matheusmt.pvd.pvd.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String description;
    @Column(nullable = false, precision = 20,scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<ItemSale> itemSales;
}
