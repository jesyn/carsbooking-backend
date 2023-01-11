package com.dh.PIG11.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacteristicDTO {
    private Long id;

    private String characteristic;

    private String url_icon;
}
