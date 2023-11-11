package com.example.dataProcessing;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.dataProcessing.model.ProcessedData;
import com.example.dataProcessing.repository.DataRepository;

@Component
public class KafkaListeners {

    private DataRepository repository;
    
    public KafkaListeners(DataRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "proccesedData", groupId = "proccesed_Data")
    void listener(String data){

        ProcessedData obj = new ProcessedData();
        obj.setMessage(data);

        repository.save(obj);

        System.out.println(data);

    }
}
