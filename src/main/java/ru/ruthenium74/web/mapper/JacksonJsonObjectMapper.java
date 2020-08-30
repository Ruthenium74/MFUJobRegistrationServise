package ru.ruthenium74.web.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonJsonObjectMapper extends ObjectMapper {
    private static ObjectMapper MAPPER = new JacksonJsonObjectMapper();

    private JacksonJsonObjectMapper() {
        registerModule(new JavaTimeModule());

        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
