package com.example.dataProcessing.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.dataProcessing.model.ProcessedData;

@Configuration
public class KafkaConsumerConfig {
    
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String,Object> consumerConfig(){

       HashMap<String,Object> props =   new HashMap<>();
       props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
       props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaValueDeserializer.class);
       props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaValueDeserializer.class);

       return props;
    }

    @Bean
    public ConsumerFactory<String, ProcessedData> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // default KafkaListener -> not valid for our custom object 
    // public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,ProcessedData>> factory(ConsumerFactory<String, ProcessedData> consumerFactory ){
    //     ConcurrentKafkaListenerContainerFactory<String, ProcessedData> factory = new ConcurrentKafkaListenerContainerFactory<>();
    //     factory.setConsumerFactory(consumerFactory);
    //     return factory;
    // }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProcessedData> 
    exampleKafkaListenerContainerFactory(ConsumerFactory<String, ProcessedData> consumerFactory ) {

        ConcurrentKafkaListenerContainerFactory<String, ProcessedData> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
