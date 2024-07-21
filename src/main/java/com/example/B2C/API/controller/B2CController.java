package com.example.B2C.API.controller;

import com.example.B2C.API.entities.GwRequests;
import com.example.B2C.API.entities.Result;
import com.example.B2C.API.services.B2CService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/b2c")
public class B2CController {

    @Autowired
    private B2CService b2CService;

    @PostMapping("/request")
    public ResponseEntity<String> receiveB2CRequest(@Valid @RequestBody GwRequests gwRequest) {
        b2CService.processB2CRequest(gwRequest);
        return ResponseEntity.ok("B2C request received");
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Result> fetchPaymentStatus(@PathVariable UUID id) {
        Result result = b2CService.fetchPaymentStatus(id);
        return ResponseEntity.ok(result);
    }
}
