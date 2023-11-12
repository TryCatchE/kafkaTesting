package com.example.dataProcessing.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.dataProcessing.model.ProcessedData;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String,Object> producerConfig(){

       HashMap<String,Object> props =   new HashMap<>();
       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
       props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaValueSerializer.class);
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaValueSerializer.class);

       return props;
    }

    // change the second string to class
    @Bean
    public ProducerFactory<String, ProcessedData> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    
    // change this to specific datatype
    @Bean
    public KafkaTemplate<String,ProcessedData> kafkaTemplate(ProducerFactory<String, ProcessedData> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
