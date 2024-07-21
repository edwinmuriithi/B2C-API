package com.example.B2C.API.services;

import static org.junit.jupiter.api.Assertions.*;
import com.example.B2C.API.entities.GwRequests;
import com.example.B2C.API.entities.Result;
import com.example.B2C.API.repositories.GwRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class B2CServiceTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private GwRequestRepository gwRequestRepository;

    @InjectMocks
    private B2CService b2cService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processB2CRequest() {
        // Arrange
        GwRequests gwRequest = new GwRequests();
        gwRequest.setId(UUID.randomUUID());
        gwRequest.setStatus("New");

        // Act
        b2cService.processB2CRequest(gwRequest);

        // Assert
        assertEquals("Pending", gwRequest.getStatus());
        verify(gwRequestRepository, times(1)).save(gwRequest);
        verify(kafkaTemplate, times(1)).send(eq("b2c-requests"), eq(gwRequest.getId().toString()), anyString());
    }


}