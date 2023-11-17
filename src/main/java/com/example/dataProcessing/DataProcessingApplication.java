package com.example.dataProcessing;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.dataProcessing.model.ProcessedData;
import com.example.dataProcessing.repository.DataRepository;

@SpringBootApplication
public class DataProcessingApplication {

	private DataRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,ProcessedData> kafkaTemplate, DataRepository repository){

		ProcessedData data = new ProcessedData();
		data.setMessage("asdasd");

		return args->{

			for(int i =0; i<200; i++){
				data.setSex(new Random().nextBoolean() ? "M" : "F");
				kafkaTemplate.send("newTopic", data);
			}

			// while (true) {
			// 	

			// 	// TimeUnit.SECONDS.sleep(5); 
			// }
		};
	}

}
