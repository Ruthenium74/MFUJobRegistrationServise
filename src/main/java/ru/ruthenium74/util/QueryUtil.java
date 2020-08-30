package ru.ruthenium74.util;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import ru.ruthenium74.model.JobType;

import java.time.LocalDateTime;

public class QueryUtil {
    private QueryUtil() {
    }

    public static String getFilteredQuery(String user, JobType type, String device, LocalDateTime from, LocalDateTime to,
                                          MapSqlParameterSource map) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM mfu_jobs WHERE");
        boolean isQueryFiltered = false;
        if (user != null) {
            isQueryFiltered = true;
            stringBuffer.append(" mfu_jobs.user=:user AND");
            map.addValue("user", user);
        }
        if (type != null) {
            isQueryFiltered = true;
            stringBuffer.append(" type=:type AND");
            map.addValue("type", type.name());
        }
        if (device != null) {
            isQueryFiltered = true;
            stringBuffer.append(" device=:device AND");
            map.addValue("device", device);
        }
        if (from != null) {
            isQueryFiltered = true;
            stringBuffer.append(" time>=:fromTime AND");
            map.addValue("fromTime", from);
        }
        if (to != null) {
            isQueryFiltered = true;
            stringBuffer.append(" time<:toTime AND");
            map.addValue("toTime", to);
        }
        if (isQueryFiltered) {
            int lastIndexOfAnd = stringBuffer.lastIndexOf(" AND");
            if (lastIndexOfAnd > 0) {
                stringBuffer = new StringBuffer(stringBuffer.substring(0, lastIndexOfAnd));
            }
        } else {
            stringBuffer = new StringBuffer(stringBuffer.toString().replace(" WHERE", ""));
        }
        return stringBuffer.append(" ORDER BY time").toString();
    }
}
