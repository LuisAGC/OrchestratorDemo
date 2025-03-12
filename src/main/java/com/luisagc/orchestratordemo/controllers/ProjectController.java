package com.luisagc.orchestratordemo.controllers;

import com.luisagc.orchestratordemo.models.ProjectRequest;
import com.luisagc.orchestratordemo.models.ProjectResponse;
import com.luisagc.orchestratordemo.services.ProjectOrchestrator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ProjectController {

    private final ProjectOrchestrator projectOrchestrator;

    public ProjectController(ProjectOrchestrator projectOrchestrator) {

        this.projectOrchestrator = projectOrchestrator;
    }

    @PostMapping
    public Mono<ProjectResponse> createProject(@RequestBody ProjectRequest project) {
        return projectOrchestrator.createProject(project);
    }

}
