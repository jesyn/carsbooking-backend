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
public class CityDTO {
    private Long id;

    private String city;

    private String province;

    private String country;

    private String latitude;

    private String longitude;

    private String address;
}
