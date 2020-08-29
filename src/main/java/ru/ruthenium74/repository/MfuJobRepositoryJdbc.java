package ru.ruthenium74.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ruthenium74.model.JobType;
import ru.ruthenium74.model.MfuJob;
import ru.ruthenium74.util.QueryUtil;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MfuJobRepositoryJdbc implements MfuJobRepository {

    private final JdbcTemplate jdbcTemplate;

    public MfuJobRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(List<MfuJob> jobs) {
        jdbcTemplate.batchUpdate("INSERT INTO mfu_jobs (id, type, user, device, amount, time) VALUES (?,?,?,?,?,?)",
                jobs, jobs.size(), (preparedStatement, job) -> {
                    preparedStatement.setInt(1, job.getId());
                    preparedStatement.setString(2, job.getJobType().name());
                    preparedStatement.setString(3, job.getUser());
                    preparedStatement.setString(4, job.getDevice());
                    preparedStatement.setInt(5, job.getAmount());
                    preparedStatement.setObject(6, job.getDate());
                });
    }

    public List<MfuJob> getFiltered(String user, JobType type, String device, LocalDateTime from, LocalDateTime to) {
        String query = QueryUtil.getFilteredQuery(user, type, device, from, to);
        return jdbcTemplate.query(query, (resultSet, i) -> {
            MfuJob newJob = new MfuJob();
            newJob.setId(resultSet.getInt("id"));
            newJob.setJobType(JobType.valueOf(resultSet.getString("type")));
            newJob.setUser(resultSet.getString("user"));
            newJob.setDevice(resultSet.getString("device"));
            newJob.setAmount(resultSet.getInt("amount"));
            newJob.setDate(resultSet.getObject("time", LocalDateTime.class));
            return newJob;
        });
    }
}
