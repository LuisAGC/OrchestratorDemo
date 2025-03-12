package com.luisagc.orchestratordemo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ProjectEntity extends BaseEntity {

    private String name;

}
