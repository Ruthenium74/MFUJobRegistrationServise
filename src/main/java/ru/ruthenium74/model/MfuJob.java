package ru.ruthenium74.model;

public class MfuJob {
    private int id;
    private JobType jobType;
    private String user;
    private String device;
    private String amount;

    public MfuJob(int id, JobType jobType, String user, String device, String amount) {
        this.id = id;
        this.jobType = jobType;
        this.user = user;
        this.device = device;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
