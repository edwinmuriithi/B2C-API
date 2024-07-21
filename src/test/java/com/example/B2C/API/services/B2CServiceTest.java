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
        GwRequests gwRequest = new GwRequests();
        gwRequest.setId(UUID.randomUUID());
        gwRequest.setStatus("New");

        b2cService.processB2CRequest(gwRequest);

        assertEquals("Pending", gwRequest.getStatus());
        verify(gwRequestRepository, times(1)).save(gwRequest);
        verify(kafkaTemplate, times(1)).send(eq("b2c-requests"), eq(gwRequest.getId().toString()), anyString());
    }

    @Test
    void fetchPaymentStatus() {

        UUID id = UUID.randomUUID();
        GwRequests gwRequest = new GwRequests();
        gwRequest.setId(id);
        gwRequest.setStatus("Completed");
        when(gwRequestRepository.findById(id)).thenReturn(Optional.of(gwRequest));


        Result result = b2cService.fetchPaymentStatus(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Completed", result.getStatus());
        verify(gwRequestRepository, times(1)).findById(id);
    }

    @Test
    void updatePaymentStatus() {
        UUID id = UUID.randomUUID();
        Result result = new Result();
        result.setId(id);
        result.setStatus("Completed");

        GwRequests gwRequest = new GwRequests();
        gwRequest.setId(id);
        gwRequest.setStatus("Pending");
        when(gwRequestRepository.findById(id)).thenReturn(Optional.of(gwRequest));

        b2cService.updatePaymentStatus(result);

        assertEquals("Completed", gwRequest.getStatus());
        verify(gwRequestRepository, times(1)).findById(id);
        verify(gwRequestRepository, times(1)).save(gwRequest);
    }

}