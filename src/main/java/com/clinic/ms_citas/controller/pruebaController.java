package com.clinic.ms_citas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("/test")
public class pruebaController {

    private final DataSource dataSource;

    public pruebaController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public String prueba() {
        return "¡La aplicación está funcionando correctamente!";
    }

    @GetMapping("/test-db-connection")
    public String testDbConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "¡Conexión a la base de datos exitosa!";
        } catch (Exception e) {
            return "Error al conectar con la base de datos: " + e.getMessage();
        }
    }
}