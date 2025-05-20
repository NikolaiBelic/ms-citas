package com.clinic.ms_citas.config;

import com.clinic.ms_citas.utils.CustomSqlTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;

public class GsonConfig {
    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Time.class, new CustomSqlTimeAdapter())
                .create();
    }
}
