package ru.hpclab.bd.module1.service.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Represents time information retrieved from an external time service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeInfo {
    private String abbreviation; // "MSK"
    private String clientIp; // "94.243.149.101",
    private OffsetDateTime datetime; // "2023-11-07T20:17:18.397605+03:00",
    private short dayOfWeek; // 2,
    private short dayOfYear; // 311,
    private boolean dst; // false,
    private Object dstFrom; // null,
    private long dstOffset; // 0,
    private Object dstUntil; // null,
    private long rawOffset; // 10800,
    private String timezone; // "Europe/Moscow",
    private long unixtime; // 1699377438,
    private OffsetDateTime utcDatetime; // "2023-11-07T17:17:18.397605+00:00",
    private String utcOffset; // "+03:00",
    private short weekNumber; // 45

    /**
     * Get the OffsetDateTime representing the datetime information.
     *
     * @return The OffsetDateTime representing the datetime information.
     */
    public OffsetDateTime getDatetime() {
        return datetime;
    }
}
