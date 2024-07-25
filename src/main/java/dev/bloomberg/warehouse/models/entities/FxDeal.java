package dev.bloomberg.warehouse.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "fx_deal")
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FxDeal extends _AbstractEntity {
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private Double dealAmount;
}
