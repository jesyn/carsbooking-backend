package com.dh.PIG11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
@CrossOrigin
public class City {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String city;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column
    private String address;
    /*@OneToMany(mappedBy = "city")
    @JsonManagedReference
    @JsonIgnore
    private Set<Product> products= new HashSet<>();*/




}
