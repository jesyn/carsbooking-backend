package com.dh.PIG11.repository;

import com.dh.PIG11.entities.Booking;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("select b from Booking b join Product p on b.product.id = p.id where p.id = ?1")
    List<Booking> findAllByProductId(Long product_id);
    @Query("select b from Booking b join User u on b.user.id = u.id where u.id = ?1")
    List<Booking> findAllByUserId(Long user_id);


}
