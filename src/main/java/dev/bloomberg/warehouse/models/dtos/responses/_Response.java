package dev.bloomberg.warehouse.models.dtos.responses;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Marker interface for response data transfer objects (DTOs).
 * This interface serves as a common type for all response DTOs.
 *
 * @version 1.0
 */
public interface  _Response<ID> extends Serializable {
    /**
     * Gets the unique identifier of the DTO.
     *
     * @return The identifier of the DTO.
     */
    ID getId();

    /**
     * Gets the timestamp when the DTO was created.
     *
     * @return The creation timestamp of the DTO.
     */
    Timestamp getTimestamp();
}
