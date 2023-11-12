package com.example.dataProcessing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Document("processedData")
@Data
@NoArgsConstructor
// @RequiredArgsConstructor
@ToString
public class ProcessedData {
    @Id
    private String id;

    // @NonNull
    private String message;

    // @NonNull
    private String sex;
}
