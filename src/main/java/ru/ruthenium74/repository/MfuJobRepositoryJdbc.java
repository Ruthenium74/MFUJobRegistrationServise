package ru.ruthenium74.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ruthenium74.model.JobType;
import ru.ruthenium74.model.MfuJob;
import ru.ruthenium74.util.QueryUtil;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public class MfuJobRepositoryJdbc implements MfuJobRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MfuJobRepositoryJdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    public void save(List<MfuJob> jobs) {
        jdbcTemplate.batchUpdate("INSERT INTO mfu_jobs (id, type, user, device, amount, time) VALUES (?,?,?,?,?,?)",
                jobs, jobs.size(), (preparedStatement, job) -> {
                    preparedStatement.setInt(1, job.getId());
                    preparedStatement.setString(2, job.getType().name());
                    preparedStatement.setString(3, job.getUser());
                    preparedStatement.setString(4, job.getDevice());
                    preparedStatement.setInt(5, job.getAmount());
                    preparedStatement.setObject(6, job.getTime());
                });
    }

    public List<MfuJob> getFiltered(String user, String type, String device, LocalDateTime from, LocalDateTime to) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        String query = QueryUtil.getFilteredQuery(user, type == null ? null : JobType.valueOf(type.toUpperCase()),
                device, from, to, map);
        return namedParameterJdbcTemplate.query(query, map, (resultSet, i) -> {
            MfuJob newJob = new MfuJob();
            newJob.setId(resultSet.getInt("id"));
            newJob.setType(JobType.valueOf(resultSet.getString("type")));
            newJob.setUser(resultSet.getString("user"));
            newJob.setDevice(resultSet.getString("device"));
            newJob.setAmount(resultSet.getInt("amount"));
            newJob.setTime(resultSet.getObject("time", LocalDateTime.class));
            return newJob;
        });
    }
}
