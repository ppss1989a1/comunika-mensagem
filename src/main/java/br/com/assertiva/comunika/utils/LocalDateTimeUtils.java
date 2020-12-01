package br.com.assertiva.comunika.utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    private static final String PADRAO_DATA_HORA = "dd/MM/yyyy HH:mm:ss";

    private static final String ZONE_FORTALEZA = "America/Fortaleza";

    private static final ZoneId zoneId = ZoneId.of(ZONE_FORTALEZA);

    public static DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern(PADRAO_DATA_HORA);

    public static Clock clock = Clock.system(zoneId);

    public LocalDateTime nowFortaleza() {
        return stringToDate(LocalDateTime.now(clock).format(formatterDataHora));
    }

    public LocalDateTime stringToDate(String dataString) {

        return LocalDateTime.parse(dataString, formatterDataHora);
    }

    public LocalDateTime formatLocalDate(LocalDateTime date) {
        return LocalDateTime.parse(date.format(formatterDataHora));
    }
}