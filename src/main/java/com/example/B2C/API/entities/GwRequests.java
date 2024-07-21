package com.example.B2C.API.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "gwRequests")
public class GwRequests {

    @Id
    private UUID id;
    @NotNull
    private float amount;
    @NotNull
    private String mobileNumber;
    private String status;
}
