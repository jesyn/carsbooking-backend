package com.dh.PIG11.repository;

import com.dh.PIG11.entities.Image;
import com.dh.PIG11.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    //@Query("select i from Image i join Product p on i.product.id = i.id where i.id = ?1")
    //List<Product> findAllByCityId(Long category_id);
}
