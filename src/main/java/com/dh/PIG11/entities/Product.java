package com.dh.PIG11.entities;

import com.dh.PIG11.dto.CharacteristicDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
//@IdClass(ProductCharacteristic.class)

public class Product  {
    //private static final long serialVersionUID = -536142564168467268L;

    @Id
    //@EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String description;
    @Column
    private String patente;
    @Column
    private String portada_url;

    @Column
    private String rules;
    @Column
    private String insurance;
    @Column
    private String cancellation;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;



    /*@JoinTable(name = "products_characteristics",
            joinColumns =  @JoinColumn( name="product_id") ,
            inverseJoinColumns =  @JoinColumn(name = "characteristic_id") )
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    //CascadeType.ALL,
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)*/

    //private List<Characteristic> characteristics= new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    /*public void addCharacteristic(Characteristic characteristic) {
        ProductCharacteristic productCharacteristic = new ProductCharacteristic(this, characteristic);
        characteristics.add(productCharacteristic.getCharacteristic());
        //characteristic.getProductosXcaracteristica().add(productCharacteristic.getProduct());
    }*/

    /*public void removeTag(Characteristic characteristic) {
        for (Iterator<ProductCharacteristic> iterator = characteristics.iterator();
             iterator.hasNext(); ) {
            ProductCharacteristic productCharacteristic = iterator.next();

            if (postTag.getPost().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
            }
        }
    }*/



}
