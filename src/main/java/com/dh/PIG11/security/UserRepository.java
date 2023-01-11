package com.dh.PIG11.security;

import com.dh.PIG11.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);

    @Query(value = "SELECT count(*) FROM users where email like(?1)",nativeQuery = true)
    public int validateEmail(String email);


}
