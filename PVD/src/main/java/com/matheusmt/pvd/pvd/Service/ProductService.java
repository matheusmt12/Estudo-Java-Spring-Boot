package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.Repository.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }




}
