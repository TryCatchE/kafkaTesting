package com.example.dataProcessing.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataProcessing.model.ProcessedData;
import com.example.dataProcessing.repository.DataRepository;

@RestController
@CrossOrigin
public class DataController {
    
    private final DataRepository repository;

    public DataController(DataRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<ProcessedData> test(){
        return repository.findAll();
    }

    @PostMapping("/post")
    public void  test1(@RequestBody ProcessedData data){

        repository.save(data);

    }

    
}
