package com.example.putdefault;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PutDefaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(PutDefaultApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                .enable(JsonParser.Feature.ALLOW_MISSING_VALUES).enable(JsonParser.Feature.IGNORE_UNDEFINED);
    }
}
