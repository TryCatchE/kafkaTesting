package com.example.dataProcessing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.dataProcessing.model.ProcessedData;

public interface DataRepository extends MongoRepository<ProcessedData, String> {

    long countBySex(String sex);
    
    default long countBySexM() {
        return countBySex("M");
    }

    default long countBySexF() {
        return countBySex("F");
    }
    
}
