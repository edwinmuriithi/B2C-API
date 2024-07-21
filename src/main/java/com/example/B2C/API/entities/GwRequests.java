package com.example.B2C.API.entities;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotNull
    public float getAmount() {
        return amount;
    }

    public void setAmount(@NotNull float amount) {
        this.amount = amount;
    }

    public @NotNull String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(@NotNull String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
