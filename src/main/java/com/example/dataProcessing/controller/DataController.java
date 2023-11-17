package com.example.dataProcessing.controller;
import java.util.List;
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
    public TodoDto test(){
        // return repository.findAll();

        Long females = repository.countBySexF();
        Long males = repository.countBySexM();

        TodoDto todoDto = new TodoDto();
        todoDto.setFemales(females);
        todoDto.setMales(males);
        
        return todoDto;
    }

    @PostMapping("/post")
    public void  test1(@RequestBody ProcessedData data){

        // repository.save(data);
        kafkaTemplate.send("proccesedData",data);

    }

    @GetMapping("/kafka")
    public void est1(){

        ProcessedData data = new ProcessedData();
        data.setMessage("FROM CONTROLLER");

        kafkaTemplate.send("proccesedData",data);

    }

    @GetMapping("/deleteAll")
    public void est(){

        repository.deleteAll();

    }


    
}
