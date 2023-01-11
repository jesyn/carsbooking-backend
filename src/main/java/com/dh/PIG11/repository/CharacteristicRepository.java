package com.dh.PIG11.repository;

import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic,Long> {
    @Query(value = "select c.* from products_characteristics pc join characteristics c on pc.characteristic_id = c.id where pc.product_id =?1", nativeQuery = true)
    List<Characteristic> findAllByProductId(Long product_id);

}
