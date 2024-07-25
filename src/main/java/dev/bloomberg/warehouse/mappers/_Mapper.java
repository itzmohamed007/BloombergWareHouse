package dev.bloomberg.warehouse.mappers;


import dev.bloomberg.warehouse.models.dtos.requests._Request;
import dev.bloomberg.warehouse.models.dtos.responses._Response;
import dev.bloomberg.warehouse.models.entities._Entity;

import java.sql.Timestamp;

/**
 * Generic mapper interface for converting between DTOs (Data Transfer Objects) and entities.
 *
 * @param <ID>     The type of the entity's identifier.
 * @param <Req>    The type of the request DTO.
 * @param <Res>    The type of the response DTO.
 * @param <Entity> The type of the entity.
 */
public interface _Mapper<ID, Req extends _Request<ID>, Res extends _Response<ID>, Entity extends _Entity<ID>> {
    /**
     * Converts a request DTO to an entity.
     *
     * @param request Request DTO.
     * @return Converted entity.
     */
    Entity toEntityFromRequest(Req request);

    /**
     * Converts an entity to a response DTO.
     *
     * @param entity Entity.
     * @return Converted response DTO.
     */
    Res toResponse(Entity entity);

    /**
     * Maps a string representing the creation timestamp to a {@link Timestamp} object.
     *
     * @param timestamp String representation of the creation timestamp.
     * @return {@link Timestamp} object representing the creation timestamp.
     */
    default Timestamp mapTimestamp(String timestamp) {
        if (timestamp == null) {
            return null;
        }
        return Timestamp.valueOf(timestamp);
    }
}