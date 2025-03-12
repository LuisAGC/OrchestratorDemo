package com.luisagc.orchestratordemo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class AddressEntity extends BaseEntity {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
