package ru.ruthenium74.util;

import ru.ruthenium74.model.JobType;

import java.time.LocalDateTime;

public class QueryUtil {
    private QueryUtil() {
    }

    public static String getFilteredQuery(String user, JobType type, String device, LocalDateTime from, LocalDateTime to) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM mfu_jobs WHERE");
        boolean isQueryFiltered = false;
        if (user != null) {
            isQueryFiltered = true;
            stringBuffer.append(" user='").append(user).append("' AND");
        }
        if (type != null) {
            isQueryFiltered = true;
            stringBuffer.append(" type='").append(type.name()).append("' AND");
        }
        if (device != null) {
            isQueryFiltered = true;
            stringBuffer.append(" device='").append(device).append("' AND");
        }
        if (from != null) {
            isQueryFiltered = true;
            stringBuffer.append(" time>=").append(from).append(" AND");
        }
        if (to != null) {
            isQueryFiltered = true;
            stringBuffer.append(" time<").append(to).append(" AND");
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
