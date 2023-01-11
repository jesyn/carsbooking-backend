package com.dh.PIG11.dto;

import com.dh.PIG11.entities.Product;
import com.dh.PIG11.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {

    private Long id;

    private Date start_date;

    private Date end_date;

    private ProductDTO product;

    private UserDTO user;
}
