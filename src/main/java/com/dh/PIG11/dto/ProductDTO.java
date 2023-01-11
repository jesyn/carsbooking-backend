
package com.dh.PIG11.dto;


import com.dh.PIG11.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;

    private String brand;

    private String model;

    private String description;

    private String patente;

    private String portada_url;

    private String rules;

    private String insurance;

    private String cancellation;

    private CityDTO city;

    private CategoryDTO category;

    private List<CharacteristicDTO> characteristics = new ArrayList<>();

    private List<ImageDTO> images = new ArrayList<>();


}

