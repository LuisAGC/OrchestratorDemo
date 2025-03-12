package com.luisagc.orchestratordemo.mappers;

import com.luisagc.orchestratordemo.entities.ProjectEntity;
import com.luisagc.orchestratordemo.models.ProjectRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectRequestToEntityMapper implements BaseMapper<ProjectRequest, ProjectEntity>{

    @Override
    public ProjectEntity map(ProjectRequest projectRequest) {
        return ProjectEntity.builder().name(projectRequest.getName()).build();
    }

}
