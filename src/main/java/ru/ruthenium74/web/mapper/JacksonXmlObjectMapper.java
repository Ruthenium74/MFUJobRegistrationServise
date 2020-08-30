package ru.ruthenium74.web.mapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonXmlObjectMapper extends XmlMapper {
    private static final XmlMapper MAPPER = new JacksonXmlObjectMapper();

    private JacksonXmlObjectMapper() {
        registerModule(new JavaTimeModule());

        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static XmlMapper getMapper() {
        return MAPPER;
    }
}
