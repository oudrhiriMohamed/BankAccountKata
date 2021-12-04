package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String toDayDateAsStringFormat() {
        LocalDate today = LocalDate.now();
        return today.format(DD_MM_YYYY);

    }
}
