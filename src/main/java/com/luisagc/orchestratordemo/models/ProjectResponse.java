package com.luisagc.orchestratordemo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    private String id;
    private String name;
    private AddressResponse address;
}
