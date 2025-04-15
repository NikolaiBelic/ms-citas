package com.clinic.ms_citas.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CLINIC_CITA")
public class Cita {

    @Id
    @GeneratedValue
    private UUID id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DIA", nullable = false)
    private Date dia;

    @Column(name = "HORA_INICIO", nullable = false)
    private Time horaInicio;

    @Column(name = "HORA_FINAL", nullable = false)
    private Time horaFinal;

    @Column(name = "PACIENTE_ID")
    private UUID pacienteId;

    @Column(name = "ESPECIALISTA_ID")
    private UUID especialistaId;

    @Column(name = "SERVICIO_ID")
    private UUID servicioId;

    @Column(name = "PAGADO", nullable = false)
    private Boolean pagado = false;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    public UUID getEspecialistaId() {
        return especialistaId;
    }

    public void setEspecialistaId(UUID especialistaId) {
        this.especialistaId = especialistaId;
    }

    public UUID getServicioId() {
        return servicioId;
    }

    public void setServicioId(UUID servicioId) {
        this.servicioId = servicioId;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", dia=" + dia +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                ", pacienteId=" + pacienteId +
                ", especialistaId=" + especialistaId +
                ", servicioId=" + servicioId +
                ", pagado=" + pagado +
                '}';
    }
}
