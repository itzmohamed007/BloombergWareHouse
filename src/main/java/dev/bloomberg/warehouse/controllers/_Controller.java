package dev.bloomberg.warehouse.controllers;


import dev.bloomberg.warehouse.models.dtos.requests._Request;
import dev.bloomberg.warehouse.models.dtos.responses._Response;
import dev.bloomberg.warehouse.services.spec._Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Generic controller with CRUD operations for DTOs.
 *
 * @param <ID> The type of the identifier.
 * @param <Request>> The request DTO type.
 * @param <Response> The response DTO type.
 * @param <Service> The service type implementing _service.
 */
@Slf4j
@Getter
@RestController
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class _Controller<ID, Request extends _Request<ID>, Response extends _Response<ID>, Service extends _Service<ID, Request, Response>> {
    protected Service service;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setService(Service service) {
        this.service = service;
    }

    /**
     * Creates a new entity based on the provided request.
     *
     * @param request The request DTO.
     * @return ResponseEntity containing the created entity or a bad request if creation fails.
     */
    @PostMapping
    public ResponseEntity<Response> create(
            @Valid @RequestBody Request request
    ) {
        assert service != null;
        Optional<Response> response = service.create(request);

        return response.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}

