package com.example.dataProcessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.dataProcessing.model.ProcessedData;

public interface DataRepository extends MongoRepository<ProcessedData, String> {

    
    
}
