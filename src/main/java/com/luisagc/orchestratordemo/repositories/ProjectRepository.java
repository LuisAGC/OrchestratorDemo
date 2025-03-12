package com.luisagc.orchestratordemo.repositories;

import com.luisagc.orchestratordemo.entities.ProjectEntity;
import com.luisagc.orchestratordemo.mappers.BaseMapper;
import com.luisagc.orchestratordemo.models.ProjectResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProjectRepository {

    BaseMapper<ProjectEntity, ProjectResponse> mapper;

    public ProjectRepository(BaseMapper<ProjectEntity, ProjectResponse> mapper) {
        this.mapper = mapper;
    }

    public Mono<ProjectResponse> saveProject(ProjectEntity entity) {
        entity.setId("1");
        return Mono.just(mapper.map(entity));
    }

}
