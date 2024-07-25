package dev.bloomberg.warehouse.services.impl;


import dev.bloomberg.warehouse.exceptions.customs.ModularException;
import dev.bloomberg.warehouse.mappers.FxDealMapper;
import dev.bloomberg.warehouse.models.dtos.requests.FxDealRequest;
import dev.bloomberg.warehouse.models.dtos.responses.FxDealResponse;
import dev.bloomberg.warehouse.models.entities.FxDeal;
import dev.bloomberg.warehouse.repositories.FxDealRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FxDealService extends _AbstractService<Long, FxDealRequest, FxDealResponse, FxDeal, FxDealRepository, FxDealMapper> {
    private final FxDealRepository repository;
    private final FxDealMapper mapper;

    @Override
    public Optional<FxDealResponse> create(@Valid FxDealRequest request) {
        FxDeal fxDeal = mapper.toEntityFromRequest(request);
        if(isDuplicate(fxDeal))
            throw new ModularException("FxDeal with id " + fxDeal.getId() + " is duplicated", HttpStatus.BAD_REQUEST);

        try {
            FxDeal createdFxDeal = repository.saveAndFlush(fxDeal);
            return Optional.of(mapper.toResponse(createdFxDeal));
        } catch (Exception e) {
            throw new ModularException("an error occurred while creating FxDeal " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isDuplicate(FxDeal fxDeal) {
        try {
            return repository.existsById(fxDeal.getId());
        } catch (Exception e) {
            throw new ModularException("an error occurred while checking if FxDeal is a duplicate", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
