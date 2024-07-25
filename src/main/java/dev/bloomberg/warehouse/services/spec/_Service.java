package dev.bloomberg.warehouse.services.spec;


import dev.bloomberg.warehouse.models.dtos.requests._Request;
import dev.bloomberg.warehouse.models.dtos.responses._Response;
import jakarta.validation.Valid;

import java.util.Optional;

/**
 * Generic service interface with common CRUD (Create, Read, Update, Delete) operations.
 *
 * @param <ID>  The type of the unique identifier.
 * @param <Req> The request DTO type.
 * @param <Res> The response DTO type.
 */
public interface _Service<ID, Req extends _Request<ID>, Res extends _Response<ID>> {
    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    Optional<Res> create(@Valid Req request);
}