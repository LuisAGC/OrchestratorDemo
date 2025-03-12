package com.luisagc.orchestratordemo.executors;

import com.luisagc.orchestratordemo.entities.AddressEntity;
import com.luisagc.orchestratordemo.mappers.BaseMapper;
import com.luisagc.orchestratordemo.models.AddressRequest;
import com.luisagc.orchestratordemo.models.AddressResponse;
import com.luisagc.orchestratordemo.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class CreateAddressExecutor extends ExecutorTemplate<AddressRequest, Optional<AddressResponse>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateAddressExecutor.class);
    private final BaseMapper<AddressRequest, AddressEntity> requestToEntityMapper;
    private final AddressRepository addressRepository;

    public CreateAddressExecutor(BaseMapper<AddressRequest, AddressEntity> requestToEntityMapper, AddressRepository addressRepository) {
        this.requestToEntityMapper = requestToEntityMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    protected Mono<ValidationResult> validate(AddressRequest addressRequest) {
        logger.info("Validating Address");
        if (addressRequest.getState() == null || addressRequest.getZipCode() == null || addressRequest.getZipCode().isBlank()) {
            ValidationError error = new ValidationError("1000", "Empty Zip Code");
            return Mono.just(ValidationResult.failure(List.of(error)));
        }
        return Mono.just(ValidationResult.success());
    }

    @Override
    protected Mono<Optional<AddressResponse>> logic(AddressRequest addressRequest) {
        logger.info("Saving Address");
        AddressEntity entity = requestToEntityMapper.map(addressRequest);
        return addressRepository.saveAddress(entity).flatMap(
                response -> Mono.just(Optional.of(response))
        );
    }

    @Override
    protected Mono<Void> metrics(AddressRequest addressRequest, Optional<AddressResponse> addressResponse) {
        logger.info("Recording metrics");
        return Mono.empty();
    }

    @Override
    protected Mono<Void> audit(AddressRequest addressRequest, Optional<AddressResponse> addressResponse) {
        logger.info("Recording audit");
        return Mono.empty();
    }

    @Override
    protected Mono<Optional<AddressResponse>> handleError(Throwable error) {
        logger.error("Error occurred while creating address", error);
        return Mono.error(error);
    }
}
