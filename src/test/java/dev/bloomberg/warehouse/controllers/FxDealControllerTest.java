package dev.bloomberg.warehouse.controllers;


import dev.bloomberg.warehouse.models.dtos.requests.FxDealRequest;
import dev.bloomberg.warehouse.models.dtos.responses.FxDealResponse;
import dev.bloomberg.warehouse.services.impl.FxDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FxDealControllerTest {
    @Mock
    private FxDealService service;
    private FxDealController controller;

    @BeforeEach
    public void setup() {
        controller = new FxDealController();
        controller.setService(service);
    }

    @Test
    // assure that controller is working as expected with proper arguments
    void createSuccess() {
        FxDealRequest request = FxDealRequest
                .builder()
                .id(1L)
                .build();

        FxDealResponse response = FxDealResponse
                .builder()
                .id(1L)
                .build();

        when(service.create(request)).thenReturn(Optional.of(response));
        ResponseEntity<FxDealResponse> result = controller.create(request);

        assertNotNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(result.getBody()).getId(), 1L);
    }
}