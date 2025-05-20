package com.clinic.ms_citas.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomSqlTimeAdapter extends TypeAdapter<Time> {
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void write(JsonWriter out, Time value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(TIME_FORMAT.format(value));
        }
    }

    @Override
    public Time read(JsonReader in) throws IOException {
        try {
            String timeStr = in.nextString();
            return new Time(TIME_FORMAT.parse(timeStr).getTime());
        } catch (ParseException e) {
            throw new IOException("El formato de la hora no es válido. Use el formato 'HH:mm:ss'.", e);
        }
    }
}
