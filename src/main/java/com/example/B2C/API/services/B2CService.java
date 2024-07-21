package com.example.B2C.API.services;

import com.example.B2C.API.entities.GwRequests;
import com.example.B2C.API.entities.Result;
import com.example.B2C.API.repositories.GwRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class B2CService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private GwRequestRepository gwRequestRepository;

    public void processB2CRequest(GwRequests gwRequest) {
        gwRequest.setStatus("Pending");
        gwRequestRepository.save(gwRequest);

        kafkaTemplate.send("b2c-requests", gwRequest.getId().toString(), gwRequest.toString());
    }

    public Result fetchPaymentStatus(UUID id) {
        GwRequests gwRequest = gwRequestRepository.findById(id).orElseThrow();
        Result result = new Result();
        result.setId(gwRequest.getId());
        result.setStatus(gwRequest.getStatus());
        return result;
    }

    public void updatePaymentStatus(Result result) {
        GwRequests gwRequest = gwRequestRepository.findById(result.getId()).orElseThrow();
        gwRequest.setStatus(result.getStatus());
        gwRequestRepository.save(gwRequest);
    }
}
