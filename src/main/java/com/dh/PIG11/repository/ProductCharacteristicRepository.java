package com.dh.PIG11.repository;

import com.dh.PIG11.entities.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long>{
}
