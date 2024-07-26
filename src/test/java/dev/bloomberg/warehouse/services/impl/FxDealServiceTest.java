package dev.bloomberg.warehouse.services.impl;


import dev.bloomberg.warehouse.exceptions.customs.ModularException;
import dev.bloomberg.warehouse.mappers.FxDealMapper;
import dev.bloomberg.warehouse.models.dtos.requests.FxDealRequest;
import dev.bloomberg.warehouse.models.dtos.responses.FxDealResponse;
import dev.bloomberg.warehouse.models.entities.FxDeal;
import dev.bloomberg.warehouse.repositories.FxDealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FxDealServiceTest {
    @Mock
    private FxDealRepository repository;
    @Mock
    private FxDealMapper mapper;
    @InjectMocks
    private FxDealService service;

    /**
     * declared here to reduce boilerplate code (used in all three unit tests)
     */
    private FxDealRequest fxDealRequest;
    private FxDeal fxDeal;
    private FxDealResponse fxDealResponse;

    @BeforeEach
    public void setup() {
        fxDeal = FxDeal.builder()
                .id(1L)
                .build();

        fxDealRequest = FxDealRequest.builder()
                .id(1L)
                .build();

        fxDealResponse = FxDealResponse.builder()
                .id(1L)
                .build();
    }

    /**
     * assuring that all steps are followed when data is correct
     */
    @Test
    void createSuccess() {
        when(mapper.toEntityFromRequest(fxDealRequest)).thenReturn(fxDeal);
        when(repository.existsById(fxDeal.getId())).thenReturn(false);
        when(repository.saveAndFlush(fxDeal)).thenReturn(fxDeal);
        when(mapper.toResponse(fxDeal)).thenReturn(fxDealResponse);

        Optional<FxDealResponse> result = service.create(fxDealRequest);

        assertTrue(result.isPresent());
        assertEquals(result.get(), fxDealResponse);
    }

    /**
     * assuring that an exception will be thrown if a duplicated request have been sent
     */
    @Test
    public void createDuplicateId() {
        when(mapper.toEntityFromRequest(fxDealRequest)).thenReturn(fxDeal);
        when(repository.existsById(1L)).thenReturn(true);

        ModularException exception = assertThrows(ModularException.class, () -> {
            service.create(fxDealRequest);
        });

        assertEquals("FxDeal with id 1 is duplicated", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(repository, never()).saveAndFlush(any(FxDeal.class));
    }

    /**
     * assuring that an exception is thrown when data persistence fails
     */
    @Test
    public void createFailed() {
        when(mapper.toEntityFromRequest(fxDealRequest)).thenReturn(fxDeal);
        when(repository.saveAndFlush(fxDeal)).thenThrow(ModularException.class);

        ModularException exception = assertThrows(ModularException.class, () -> {
            service.create(fxDealRequest);
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
}