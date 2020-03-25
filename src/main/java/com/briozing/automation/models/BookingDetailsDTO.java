package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsDTO {

    private String firstname;

    private String lastname;

    public Integer totalprice;

    public Boolean depositpaid;

    public BookingDatesDTO bookingdates;

    public String additionalneeds;

}





