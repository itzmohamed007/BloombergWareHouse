package dev.bloomberg.warehouse.models.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.bloomberg.warehouse.models.entities._AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/**
 * Base class for response DTOs representing {@link _AbstractEntity}.
 * _AbstractResponse includes common fields such as id and the timestamps.
 * This class is intended to be extended by specific response DTOs in the system.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class _AbstractResponse implements _Response<Long> {
    /**
     * The unique identifier of the entity.
     */
    private Long id;
    /**
     * The timestamp when the entity was created.
     */
    private Timestamp timestamp;
}
