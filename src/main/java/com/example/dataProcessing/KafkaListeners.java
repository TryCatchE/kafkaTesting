package com.example.dataProcessing;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    
    @KafkaListener(topics = "proccesedData", groupId = "proccesed_Data")
    void listener(String data){

        System.out.println(data);

    }
}
