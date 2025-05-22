package com.clinic.ms_citas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "CLINIC_ESPECIALISTA")
public class Especialista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOMBRE", length = 30)
    private String nombre;

    @Column(name = "APELLIDOS", length = 50)
    private String apellidos;

    @Column(name = "DNI", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "ESPECIALIDAD", nullable = false)
    private String especialidad;

    // Constructors
    public Especialista() {
    }

    public Especialista(String nombre, String apellidos, String dni, String especialidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.especialidad = especialidad;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}