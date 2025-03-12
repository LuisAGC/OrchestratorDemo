package com.luisagc.orchestratordemo.mappers;

import com.luisagc.orchestratordemo.entities.AddressEntity;
import com.luisagc.orchestratordemo.models.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToResponseMapper implements BaseMapper<AddressEntity, AddressResponse> {

    @Override
    public AddressResponse map(AddressEntity addressEntity) {
        return AddressResponse.builder()
                .id(Long.valueOf(addressEntity.getId()))
                .state(addressEntity.getState())
                .city(addressEntity.getCity())
                .street(addressEntity.getStreet())
                .zipCode(addressEntity.getZipCode())
                .build();
    }

}
