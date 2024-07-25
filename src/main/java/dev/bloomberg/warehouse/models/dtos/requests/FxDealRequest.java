package dev.bloomberg.warehouse.models.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FxDealRequest extends _AbstractRequest {
    @NotBlank(message = "from current iso code cannot be blank")
    @Pattern(regexp = "^[A-Z]{3}$", message = "from currency ISO code must be a 3 capital letters code")
    private String fromCurrencyIsoCode;
    @NotBlank(message = "to currency iso code cannot be blank")
    @Pattern(regexp = "^[A-Z]{3}$", message = "to currency ISO code must be a 3 capital letters code")
    private String toCurrencyIsoCode;
    @NotNull(message = "deal amount cannot be null")
    @Min(value = 1, message = "minimum amount possible cannot be less than 1")
    private Double dealAmount;
}
