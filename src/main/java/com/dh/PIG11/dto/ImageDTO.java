package com.dh.PIG11.dto;


import com.dh.PIG11.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDTO {
    private Long id;

    private String title;

    private String url;



    public ImageDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
