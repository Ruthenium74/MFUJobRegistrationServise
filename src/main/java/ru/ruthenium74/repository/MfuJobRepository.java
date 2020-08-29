package ru.ruthenium74.repository;

import ru.ruthenium74.model.JobType;
import ru.ruthenium74.model.MfuJob;


import java.util.Date;
import java.util.List;

public interface MfuJobRepository {
    void save(MfuJob job);

    List<MfuJob> getFiltered(String user, JobType type, String device, Date from, Date to);
}
