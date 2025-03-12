package com.luisagc.orchestratordemo.repositories;

import com.luisagc.orchestratordemo.entities.AddressEntity;
import com.luisagc.orchestratordemo.mappers.BaseMapper;
import com.luisagc.orchestratordemo.models.AddressResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AddressRepository {

    private final BaseMapper<AddressEntity, AddressResponse> mapper;

    public AddressRepository(BaseMapper<AddressEntity, AddressResponse> mapper) {
        this.mapper = mapper;
    }

    public Mono<AddressResponse> saveAddress(AddressEntity entity) {
        entity.setId("1");
        return Mono.just(mapper.map(entity));
    }

}
