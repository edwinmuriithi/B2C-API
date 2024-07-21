package com.example.B2C.API.repositories;

import com.example.B2C.API.entities.GwRequests;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GwRequestRepository extends MongoRepository<GwRequests, UUID> {
}
