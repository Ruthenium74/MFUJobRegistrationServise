package ru.ruthenium74.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MfuJob {
    private int id;
    private JobType type;
    private String user;
    private String device;
    private int amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime time;

    public MfuJob(int id, JobType type, String user, String device, int amount) {
        this();
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
    }

    public MfuJob() {
        time = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
