package com.clinic.ms_citas.repository;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CitaRepository extends JpaRepository<Cita, UUID> {

    /*@Query(value = """
                SELECT *
                FROM CLINIC_CITA
            """ , nativeQuery = true)
    public List<Cita> getAllCitas();*/

    @Query(value = """
                SELECT id, dia, hora_inicio, hora_final, paciente_id, especialista_id, servicio_id, pagado
                FROM CLINIC_CITA
            """ , nativeQuery = true)
    public List<Cita> getAllCitas();



    @Query("SELECT new com.clinic.ms_citas.dto.CitaDTO(c.id, c.dia, c.pacienteId, c.especialistaId, c.servicioId) " +
 "FROM Cita c")
List<CitaDTO> getAllCitasDTO();



}
