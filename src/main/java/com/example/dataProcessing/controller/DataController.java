package com.example.dataProcessing.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dataProcessing.model.ProcessedData;
import com.example.dataProcessing.model.TodoDto;
import com.example.dataProcessing.repository.DataRepository;

@RestController
@CrossOrigin
public class DataController {
    
    private final DataRepository repository;
    private KafkaTemplate<String,ProcessedData> kafkaTemplate;

    public DataController(DataRepository repository, KafkaTemplate<String,ProcessedData> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/")
    public List<TodoDto> getAll(){
        // return repository.findAll();

        Long females = repository.countBySexF();
        Long males = repository.countBySexM();

        List<TodoDto> todoDTOList = new ArrayList<>();

        Random random = new Random();
        Long randomNumber = random.nextLong();



        for(int i=0; i <3; i++){
            TodoDto todoDto = new TodoDto();
            todoDto.setFemales(females);
            todoDto.setMales( males );
            todoDto.setRandomYear(new Random().nextBoolean() ? 2016 : 2017);

            todoDTOList.add(todoDto);
        }
        
        return todoDTOList;
    }

    @PostMapping("/post")
    public void  postToKafka(@RequestBody ProcessedData data){

        // repository.save(data);
        kafkaTemplate.send("proccesedData",data);

    }

    @GetMapping("/kafka")
    public void kafkaEndpoint(){

        ProcessedData data = new ProcessedData();
        data.setMessage("FROM CONTROLLER");

        kafkaTemplate.send("proccesedData",data);

    }

    @GetMapping("/deleteAll")
    public void deleteAll(){

        repository.deleteAll();

    }


    
}
