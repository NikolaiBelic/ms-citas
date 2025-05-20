package com.clinic.ms_citas.dto;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class CitaDTO {

    private UUID id;
    private Date dia;
    private Time horaInicio;
    private Time horaFinal;
    private UUID pacienteId;
    private UUID especialistaId;
    private UUID servicioId;

    public CitaDTO(){}

    public CitaDTO(UUID id, Date dia, /*Time horaInicio, Time horaFinal,*/ UUID pacienteId,
                   UUID especialistaId, UUID servicioId/*, Boolean pagado*/) {
        this.id = id;
        this.dia = dia;
        /*this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;*/
        this.pacienteId = pacienteId;
        this.especialistaId = especialistaId;
        this.servicioId = servicioId;
        /*this.pagado = pagado;*/
    }

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

    /*public Time getHoraInicio() {
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
    }*/

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

    /*public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }*/
}
