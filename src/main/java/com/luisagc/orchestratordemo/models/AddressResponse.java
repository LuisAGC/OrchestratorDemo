package com.luisagc.orchestratordemo.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class AddressResponse {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

}
