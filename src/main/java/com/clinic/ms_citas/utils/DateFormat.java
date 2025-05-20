package com.clinic.ms_citas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormat {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static Date ajustarFechaAEspana(Date fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Interpretar como UTC
            String fechaEnUTC = sdf.format(fecha);

            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Madrid")); // Convertir a España
            return sdf.parse(fechaEnUTC);
        } catch (ParseException e) {
            throw new RuntimeException("Error al ajustar la fecha a España", e);
        }
    }
}
