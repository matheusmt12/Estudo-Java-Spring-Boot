package com.matheusmt.pvd.pvd.Repository;

import com.matheusmt.pvd.pvd.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends JpaRepository<Sale, Long> {
}
