package com.dh.PIG11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "images")
@CrossOrigin
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String url;
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    /*@OneToMany(mappedBy = "imgage")
    @JsonManagedReference
    @JsonIgnore
    private Set<Product> galería= new HashSet<>();*/
    public Image(){};

    public Long getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
        //this.product = product;
    }
    public Image( String title, String url, Product product) {

        this.title = title;
        this.url = url;
        this.product = product;
    }
}
