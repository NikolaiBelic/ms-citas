package com.clinic.ms_citas.model.paciente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CLINIC_PACIENTE")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOMBRE", length = 30)
    private String nombre;

    @Column(name = "APELLIDOS", length = 50)
    private String apellidos;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    @JsonIgnore
    private Date fechaNacimiento;

    @Column(name = "GENERO")
    @JsonIgnore
    private String genero;

    @Version
    @Column(name = "VERSION", nullable = false)
    @JsonIgnore
    protected Integer version;

    // Constructores
    public Paciente() {
    }

    public Paciente(UUID id) {
        this.id = id;
    }

    public Paciente(String nombre, String apellidos, Date fechaNacimiento, String genero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    // Getters y setters
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
