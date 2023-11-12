package com.example.dataProcessing.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.example.dataProcessing.model.ProcessedData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaValueSerializer implements Serializer<ProcessedData> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }



    @Override
    public byte[] serialize(String topic, ProcessedData data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize object {}", data, e);
            return null;
        }
    }

        @Override
    public void close() {
    }
}