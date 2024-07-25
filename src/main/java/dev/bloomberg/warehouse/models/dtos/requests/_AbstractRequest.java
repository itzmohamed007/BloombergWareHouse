package dev.bloomberg.warehouse.models.dtos.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class _AbstractRequest implements _Request<Long> {
    /**
     * The unique identifier of the entity.
     */
    @NotNull(message = "id cannot be null")
    @Positive(message = "id cannot be negative")
    private Long id;

    /**
     * The timestamp when the entity was created.
     */
    private Timestamp timestamp;
}
