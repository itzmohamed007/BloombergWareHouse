package dev.bloomberg.warehouse.services.spec;


import dev.bloomberg.warehouse.models.dtos.requests.FxDealRequest;
import dev.bloomberg.warehouse.models.dtos.responses.FxDealResponse;
import dev.bloomberg.warehouse.models.entities.FxDeal;

/**
 * {@link FxDeal} service interface
 */
public interface IFxDealService extends _Service<Long, FxDealRequest, FxDealResponse> {

}
