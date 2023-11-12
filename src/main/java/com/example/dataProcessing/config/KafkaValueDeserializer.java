package com.example.dataProcessing.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.dataProcessing.model.ProcessedData;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaValueDeserializer implements Deserializer<ProcessedData> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ProcessedData deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data, "UTF-8"), ProcessedData.class);
        } catch (Exception e) {
            log.error("Unable to deserialize message {}", data, e);
            return null;
        }
    }

    @Override
    public void close() {
    }
}