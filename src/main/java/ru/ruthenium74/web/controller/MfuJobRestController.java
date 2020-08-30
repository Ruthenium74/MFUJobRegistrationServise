package ru.ruthenium74.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ruthenium74.model.MfuJob;
import ru.ruthenium74.repository.MfuJobRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class MfuJobRestController {

    private final MfuJobRepository repository;

    public MfuJobRestController(MfuJobRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/jobs", consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveJob(@RequestBody List<MfuJob> mfuJob) {
        repository.save(mfuJob);
        return mfuJob.stream().collect(Collectors.groupingBy(MfuJob::getUser, Collector.of(
                () -> new int[1],
                (a, b) -> a[0] += b.getAmount(),
                (a, b) -> {a[0] += b[0]; return a;},
                (a) -> String.valueOf(a[0])
        )));
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MfuJob> getStatistics(@RequestParam @Nullable String user, @RequestParam @Nullable String type,
                                      @RequestParam @Nullable String device,
                                      @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
                                      @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo) {
        return repository.getFiltered(user, type, device, timeFrom, timeTo);
    }
}
