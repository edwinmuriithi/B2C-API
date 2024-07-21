package com.example.B2C.API.entities;

import lombok.Data;
import java.util.UUID;

@Data
public class Result {
    private UUID id;
    private String status;
    private String ref;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
