package com.luisagc.orchestratordemo.mappers;

import com.luisagc.orchestratordemo.entities.ProjectEntity;
import com.luisagc.orchestratordemo.models.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectEntityToResponseMapper implements BaseMapper<ProjectEntity, ProjectResponse> {

    @Override
    public ProjectResponse map(ProjectEntity entity) {
        return ProjectResponse.builder().id(entity.getId()).name(entity.getName()).build();
    }

}
