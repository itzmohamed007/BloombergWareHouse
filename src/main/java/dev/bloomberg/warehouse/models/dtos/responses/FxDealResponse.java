package dev.bloomberg.warehouse.models.dtos.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class FxDealResponse extends _AbstractResponse {
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private double dealAmount;
}
