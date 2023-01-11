package com.dh.PIG11.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ProductCharacteristic")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_characteristics")
public class ProductCharacteristic {
     @EmbeddedId
     private ProductCharacteristicId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
     private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characteristicId")
     private Characteristic characteristic;

    public ProductCharacteristic(Product product, Characteristic characteristic) {
        this.product = product;
        this.characteristic = characteristic;
        this.id = new ProductCharacteristicId(product.getId(), characteristic.getId());
    }

    public ProductCharacteristic(ProductCharacteristicId id) {
        this.id = id;
    }
}
