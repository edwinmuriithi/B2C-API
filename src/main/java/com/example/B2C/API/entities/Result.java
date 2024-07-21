package com.example.B2C.API.entities;

import lombok.Data;
import java.util.UUID;

@Data
public class Result {
    private UUID id;
    private String status;
    private String ref;
}
