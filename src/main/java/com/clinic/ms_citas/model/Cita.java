package com.clinic.ms_citas.model;

import com.clinic.ms_citas.model.paciente.Paciente;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACIENTE_ID")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESPECIALISTA_ID")
    private Especialista especialista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICIO_ID")
    private Servicio servicio;

    @Column(name = "PAGADO", nullable = false)
    private Boolean pagado = false;

    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    @Column(name = "UPDATE_TS")
    protected Date updateTs;

    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "DELETE_TS")
    protected Date deleteTs;

    @Column(name = "DELETED_BY", length = 50)
    protected String deletedBy;

    // Constructores

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

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDeleteTs() {
        return deleteTs;
    }

    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
