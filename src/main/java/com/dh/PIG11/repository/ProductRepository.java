package com.dh.PIG11.repository;

import com.dh.PIG11.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p join Category c on p.category.id = c.id where c.id = ?1")
    List<Product> findAllByCategoryId(Long category_id);
    @Query("select p from Product p join City c on p.city.id = c.id where c.id = ?1")
    List<Product> findAllByCityId(Long city_id);
    @Query(value = "select * from products order by rand() LIMIT 8",nativeQuery = true)
    List<Product> findAllRandom();
    //@Query(value="select * from products where id NOT IN (select p.id from products p join bookings b on p.id = b.product_id where  (start_date >= ?1 or end_date <= ?2));",nativeQuery = true)
    //@Query(value= "select * from products where id NOT IN (select product_id from bookings where start_date >= ?1 or end_date <= ?2)",nativeQuery = true)
    //@Query(value = "select * from products p where p.id NOT IN (select b.product_id from bookings b where (b.start_date between '?1' and '?2') and (b.end_date between '?1' and '?2'))", nativeQuery = true)
    @Query(value = "select * from products p where p.id NOT IN (select b.product_id from bookings b where (b.start_date between STR_TO_DATE(?1, '%Y-%m-%d') and STR_TO_DATE(?2, '%Y-%m-%d')) or (b.end_date between STR_TO_DATE(?1, '%Y-%m-%d') and STR_TO_DATE(?2, '%Y-%m-%d')))",nativeQuery = true)

    List<Product> findAllByDates(String start_date, String end_date);
    //@Query(value= "select * from products where id NOT IN (select product_id from bookings where start_date >= ?1 or end_date <= ?2) and city_id=?3",nativeQuery = true)
    @Query(value = "select * from products p where p.id NOT IN (select b.product_id from bookings b where (b.start_date between STR_TO_DATE(?1, '%Y-%m-%d') and STR_TO_DATE(?2, '%Y-%m-%d')) or (b.end_date between STR_TO_DATE(?1, '%Y-%m-%d') and STR_TO_DATE(?2, '%Y-%m-%d'))) and p.city_id = ?3",nativeQuery = true)

    List<Product> findAllByDatesAndCity(String start_date, String end_date, Long city_id);



   // List<Product> findAllByCharacteristicId(Long characteristic_id);

}
