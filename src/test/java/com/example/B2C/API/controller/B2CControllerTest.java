package com.example.B2C.API.controller;

import com.example.B2C.API.entities.GwRequests;
import com.example.B2C.API.entities.Result;
import com.example.B2C.API.services.B2CService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class B2CControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private B2CService b2CService;

    @InjectMocks
    private B2CController b2CController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(b2CController).build();
    }

    @Test
    void testReceiveB2CRequest() throws Exception {
        GwRequests gwRequest = new GwRequests();
        gwRequest.setId(UUID.randomUUID());
        gwRequest.setAmount(1000.0f);
        gwRequest.setMobileNumber("2547123456789");
        gwRequest.setStatus("Pending");

        doNothing().when(b2CService).processB2CRequest(any(GwRequests.class));

        mockMvc.perform(post("/b2c/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gwRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("B2C request received"));
    }

    @Test
    void testFetchPaymentStatus() throws Exception {
        UUID id = UUID.randomUUID();
        Result result = new Result();
        result.setId(id);
        result.setStatus("Completed");
        result.setRef("ABC123");

        when(b2CService.fetchPaymentStatus(id)).thenReturn(result);

        mockMvc.perform(get("/b2c/status/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }

    @Test
    void testUpdatePaymentStatus() throws Exception {
        Result result = new Result();
        result.setId(UUID.randomUUID());
        result.setStatus("Completed");
        result.setRef("DEF456");

        doNothing().when(b2CService).updatePaymentStatus(any(Result.class));

        mockMvc.perform(post("/b2c/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment status updated"));
    }
}
