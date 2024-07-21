package com.example.B2C.API.configurations;

import com.example.B2C.API.entities.GwRequests;
import com.example.B2C.API.entities.Result;
import com.example.B2C.API.repositories.GwRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListners {

    @Autowired
    private GwRequestRepository gwRequestRepository;

    @KafkaListener(topics = "b2c-responses", groupId = "daraja-group")
    public void listen(String message) throws JsonProcessingException {
        // Process response
        Result result = new ObjectMapper().readValue(message, Result.class);
        GwRequests gwRequest = gwRequestRepository.findById(result.getId()).orElseThrow();
        gwRequest.setStatus(result.getStatus());
        gwRequestRepository.save(gwRequest);
    }
}
