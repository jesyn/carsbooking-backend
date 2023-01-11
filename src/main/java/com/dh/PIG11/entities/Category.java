package com.dh.PIG11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@CrossOrigin
public class Category  {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String category;
    @Column
    private String description;
    @Column
    private String img;
    @Column
    private Double price;
    /*@OneToMany(mappedBy = "category")
    @JsonManagedReference
    @JsonIgnore
    private Set<Product> products= new HashSet<>();*/

    public Category() {
    }



    public Category(Long id, String category, String description, String img, Double price) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.img = img;
        this.price = price;
    }
    public Category(String category, String description, String img, Double price) {

        this.category = category;
        this.description = description;
        this.img = img;
        this.price = price;
    }

    public Long getId() {
        return id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
