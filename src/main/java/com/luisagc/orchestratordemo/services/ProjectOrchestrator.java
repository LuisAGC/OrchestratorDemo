package com.luisagc.orchestratordemo.services;

import com.luisagc.orchestratordemo.executors.CreateAddressExecutor;
import com.luisagc.orchestratordemo.executors.CreateProjectExecutor;
import com.luisagc.orchestratordemo.models.AddressResponse;
import com.luisagc.orchestratordemo.models.ProjectRequest;
import com.luisagc.orchestratordemo.models.ProjectResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProjectOrchestrator {

    private final CreateProjectExecutor createProjectExecutor;
    private final CreateAddressExecutor createAddressExecutor;

    public ProjectOrchestrator(CreateProjectExecutor createProjectExecutor, CreateAddressExecutor createAddressExecutor) {
        this.createProjectExecutor = createProjectExecutor;
        this.createAddressExecutor = createAddressExecutor;
    }

    public Mono<ProjectResponse> createProject(ProjectRequest projectRequest) {
        Mono<ProjectResponse> createdProject = createProjectExecutor.execute(projectRequest);
        Mono<Optional<AddressResponse>> createdAddress = createAddressExecutor.execute(projectRequest.getAddress()).defaultIfEmpty(Optional.empty());
        return Mono.zip(createdProject, createdAddress).flatMap(t -> {
                    ProjectResponse projectResponse = t.getT1();
                    Optional<AddressResponse> addressResponse = t.getT2();
                    //Optional<ContactResponse> contactResponse = t.getT3();
                    //Optional<DeliveryPreferencesResponse> deliveryPreferencesResponse = t.getT4();

                    addressResponse.ifPresent(projectResponse::setAddress);
                    //contactResponse.ifPresent(projectResponse::setContact);
                    //deliveryPreferencesResponse.ifPresent(projectResponse::setDeliveryPreferences)


                    return Mono.just(projectResponse);
                }
        );
    }

}
