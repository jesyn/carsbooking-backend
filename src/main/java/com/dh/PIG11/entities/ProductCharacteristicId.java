package com.dh.PIG11.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductCharacteristicId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "characteristic_id")
    private Long characteristicId;

}
