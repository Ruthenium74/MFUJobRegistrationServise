package ru.ruthenium74.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JobType {
    PRINT("print"),
    COPY("copy"),
    SCAN("scan"),
    FAX("fax");

    private final String type;

    JobType(String type) {
        this.type = type;
    }

    @JsonValue
    public String value() {
        return type;
    }
}
