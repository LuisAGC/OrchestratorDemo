package com.luisagc.orchestratordemo.mappers;

import com.luisagc.orchestratordemo.entities.AddressEntity;
import com.luisagc.orchestratordemo.models.AddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestToEntityMapper implements BaseMapper<AddressRequest, AddressEntity> {

    @Override
    public AddressEntity map(AddressRequest addressRequest) {
        return AddressEntity.builder()
                .state(addressRequest.getState())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .build();
    }
}
