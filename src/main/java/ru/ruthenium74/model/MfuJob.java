package ru.ruthenium74.model;

import java.time.LocalDateTime;

public class MfuJob {
    private int id;
    private JobType jobType;
    private String user;
    private String device;
    private int amount;
    private LocalDateTime date;

    public MfuJob(int id, JobType jobType, String user, String device, int amount) {
        this.id = id;
        this.jobType = jobType;
        this.user = user;
        this.device = device;
        this.amount = amount;
        date = LocalDateTime.now();
    }

    public MfuJob() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
