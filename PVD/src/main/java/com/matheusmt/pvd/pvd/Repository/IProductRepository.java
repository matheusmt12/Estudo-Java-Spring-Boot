package com.matheusmt.pvd.pvd.Repository;

import com.matheusmt.pvd.pvd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}
