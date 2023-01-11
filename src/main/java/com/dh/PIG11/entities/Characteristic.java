package com.dh.PIG11.entities;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Characteristic")
@Table(name = "characteristics")
//@NaturalIdCache
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
//@IdClass(ProductCharacteristic.class)
public class Characteristic {
    //private static final long serialVersionUID = 809423836797499302L;
    @Id
    //@EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String characteristic;
    @Column
    private String url_icon;

    //@OneToMany(mappedBy = "characteristic")
    //private List<Product> productosXcaracteristica= new ArrayList<>();

    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "characteristics")
    @JsonIgnore
    private Set<Product> products= new HashSet<>();*/



}
