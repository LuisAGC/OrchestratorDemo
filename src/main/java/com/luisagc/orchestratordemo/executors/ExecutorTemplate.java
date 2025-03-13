package com.luisagc.orchestratordemo.executors;

import com.luisagc.orchestratordemo.models.BaseRequest;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public abstract class ExecutorTemplate<T extends BaseRequest, U> {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorTemplate.class);


    public Mono<U> execute(T t) {
        logger.info("Executing request");
        if (t == null) {
            logger.warn("Request is null, returning empty Mono");
            return Mono.empty();
        }
        return validate(t) // Validate and check for errors
                .flatMap(validationResult -> {
                    if (!validationResult.valid()) {
                        // Handle validation errors and halt the process
                        logger.warn("Validation failed with errors: {}", validationResult.errors());
                        return handleValidationErrors(validationResult.errors());
                    }
                    logger.info("Validation passed, proceeding with logic");
                    return logic(t) // Proceed only if validation passes
                            .flatMap(u -> {
                                logger.info("Logic completed, proceeding with metrics");
                                return metrics(t, u).thenReturn(u);
                            }) // Append metrics as Mono<Void>
                            .flatMap(u -> {
                                logger.info("Metrics completed, proceeding with audit");
                                return audit(t, u).thenReturn(u);
                            }); // Append audit as Mono<Void>

                });
    }


    protected abstract Mono<ValidationResult> validate(T t);

    protected abstract Mono<U> logic(T t);

    protected Mono<Void> metrics(T request, U response) {
        logger.info("Recording metrics for clientId: {}", request.getClientId());
        // Add additional logic for metrics purposes
        return Mono.empty(); // Stub for metrics
    }

    protected Mono<Void> audit(T request, U response) {
        logger.info("Auditing request for clientId: {}", request.getClientId());
        logger.info("Request: {}, Response: {}", request, response);
        // Add additional logic for auditing purposes
        return Mono.empty(); // Stub for audit
    }


    // Define how to handle validation errors
    protected Mono<U> handleValidationErrors(List<ValidationError> errors) {
        // Customize the response when validation fails
        // For example: log the errors, return a fallback response, or throw an exception
        return Mono.error(new ValidationException(errors));
    }

    public record ValidationResult(boolean valid, List<ValidationError> errors) {

        public static ValidationResult success() {
            return new ValidationResult(true, List.of());
        }

        public static ValidationResult failure(List<ValidationError> errors) {
            return new ValidationResult(false, errors);
        }
    }

    // Class for individual validation errors (customize as needed)
    public record ValidationError(String code, String message) {

    }

    // Custom exception for validation issues
    @Getter
    public static class ValidationException extends RuntimeException {
        private final List<ValidationError> errors;

        public ValidationException(List<ValidationError> errors) {
            super("Validation failed with errors");
            this.errors = errors;
        }

    }

}
