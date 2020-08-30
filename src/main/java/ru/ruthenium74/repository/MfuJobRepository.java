package ru.ruthenium74.repository;

import ru.ruthenium74.model.MfuJob;


import java.time.LocalDateTime;
import java.util.List;

public interface MfuJobRepository {
    void save(List<MfuJob> jobs);

    List<MfuJob> getFiltered(String user, String type, String device, LocalDateTime from, LocalDateTime to);
}
