package dev.bloomberg.warehouse.controllers;


import dev.bloomberg.warehouse.models.dtos.requests.FxDealRequest;
import dev.bloomberg.warehouse.models.dtos.responses.FxDealResponse;
import dev.bloomberg.warehouse.services.impl.FxDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/${version}/deals")
public class FxDealController extends _Controller<Long, FxDealRequest, FxDealResponse, FxDealService> {

}
