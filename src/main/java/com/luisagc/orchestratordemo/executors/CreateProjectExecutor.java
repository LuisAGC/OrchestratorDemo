package com.luisagc.orchestratordemo.executors;

import com.luisagc.orchestratordemo.entities.ProjectEntity;
import com.luisagc.orchestratordemo.mappers.BaseMapper;
import com.luisagc.orchestratordemo.models.ProjectRequest;
import com.luisagc.orchestratordemo.models.ProjectResponse;
import com.luisagc.orchestratordemo.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateProjectExecutor extends ExecutorTemplate<ProjectRequest, ProjectResponse> {

    private static final Logger logger = LoggerFactory.getLogger(CreateProjectExecutor.class);

    ProjectRepository projectRepository;
    BaseMapper<ProjectRequest, ProjectEntity> requestToEntityMapper;

    public CreateProjectExecutor(
            ProjectRepository projectRepository,
            BaseMapper<ProjectRequest, ProjectEntity> requestToEntityMapper
    ) {
        this.projectRepository = projectRepository;
        this.requestToEntityMapper = requestToEntityMapper;
    }

    @Override
    protected Mono<ValidationResult> validate(ProjectRequest projectRequest) {
        logger.info("Validating project");
        return Mono.just(ValidationResult.success());
    }

    @Override
    protected Mono<ProjectResponse> logic(ProjectRequest projectRequest) {
        logger.info("Saving project");
        ProjectEntity entity = requestToEntityMapper.map(projectRequest);
        return projectRepository.saveProject(entity).flatMap(Mono::just);
    }

    @Override
    protected Mono<Void> metrics(ProjectRequest projectRequest, ProjectResponse projectResponse) {
        logger.info("Recording metrics");
        return Mono.empty();
    }

    @Override
    protected Mono<Void> audit(ProjectRequest projectRequest, ProjectResponse projectResponse) {
        logger.info("Recording audit");
        return Mono.empty();
    }

    @Override
    protected Mono<ProjectResponse> handleError(Throwable error) {
        logger.error("Error occurred while creating project", error);
        return Mono.error(error);
    }
}
