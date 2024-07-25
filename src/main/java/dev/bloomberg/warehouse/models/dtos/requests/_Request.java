package dev.bloomberg.warehouse.models.dtos.requests;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Marker interface for request data transfer objects (DTOs),
 * This interface serves as a common type for all request DTOs.
 */
public interface _Request<ID> extends Serializable {
    ID getId();

    /**
     * Gets the timestamp when the DTO was created.
     *
     * @return The creation timestamp of the DTO.
     */
    Timestamp getTimestamp();
}
