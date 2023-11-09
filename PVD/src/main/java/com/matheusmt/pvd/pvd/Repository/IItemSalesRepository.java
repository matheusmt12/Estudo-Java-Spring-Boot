package com.matheusmt.pvd.pvd.Repository;

import com.matheusmt.pvd.pvd.entity.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemSalesRepository extends JpaRepository<ItemSale,Long> {
}
