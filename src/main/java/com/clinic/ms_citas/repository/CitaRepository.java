package com.clinic.ms_citas.repository;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

public interface CitaRepository extends JpaRepository<Cita, UUID> {

    /*@Query(value = """
                SELECT *
                FROM CLINIC_CITA
            """ , nativeQuery = true)
    public List<Cita> getAllCitas();*/

    @Query(value = """
            SELECT * /*id, dia, hora_inicio, hora_final, paciente_id, especialista_id, servicio_id, pagado*/
            FROM CLINIC_CITA
            WHERE DELETE_TS IS NULL
        """, nativeQuery = true)
    public List<Cita> getAllCitas();

    @Query(value = """
        SELECT *
        FROM CLINIC_CITA
        WHERE DELETE_TS IS NULL
        AND (:dia IS NULL OR DIA = :dia)
        AND (:horaInicio IS NULL OR HORA_INICIO = :horaInicio)
        AND (:horaFinal IS NULL OR HORA_FINAL = :horaFinal)
        AND (:pacienteId IS NULL OR PACIENTE_ID = :pacienteId)
        AND (:especialistaId IS NULL OR ESPECIALISTA_ID = :especialistaId)
        AND (:servicioId IS NULL OR SERVICIO_ID = :servicioId)
        AND (:pagado IS NULL OR PAGADO = :pagado)
    """, nativeQuery = true)
    List<Cita> getFilteredCitas(
            @Param("dia") Date dia,
            @Param("horaInicio") Time horaInicio,
            @Param("horaFinal") Time horaFinal,
            @Param("pacienteId") UUID pacienteId,
            @Param("especialistaId") UUID especialistaId,
            @Param("servicioId") UUID servicioId,
            @Param("pagado") Boolean pagado
    );

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE CLINIC_CITA
    SET DELETE_TS = :deleteTs, DELETED_BY = :deletedBy
    WHERE ID IN (:ids)
""", nativeQuery = true)
    void softDeleteCitas(@Param("ids") List<UUID> ids, @Param("deleteTs") java.util.Date deleteTs, @Param("deletedBy") String deletedBy);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM CLINIC_CITA
        WHERE DELETE_TS IS NOT NULL
    """, nativeQuery = true)
    void deleteLogicalDeletedCitas();

    @Query(value = """
      SELECT CASE WHEN EXISTS (
        SELECT 1 FROM CLINIC_CITA c
        WHERE c.DIA = :dia
          AND c.especialista_id = :especialistaId
          AND c.ID <> :citaId
          AND CONVERT(TIME, c.HORA_INICIO) < CONVERT(TIME, :horaFinal)
          AND CONVERT(TIME, c.HORA_FINAL) > CONVERT(TIME, :horaInicio)
          AND c.DELETE_TS IS NULL
    ) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END
""", nativeQuery = true)
    boolean checkSolapamiento(
            @Param("dia") Date dia,
            @Param("horaInicio") Time horaInicio,
            @Param("horaFinal") Time horaFinal,
            @Param("especialistaId") UUID especialistaId,
            @Param("citaId") UUID citaId
    );

    @Query(value = """
    SELECT CASE WHEN COUNT(*) > 0 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END
    FROM CLINIC_CITA c
    WHERE c.DIA = '2025-06-06'
      AND c.especialista_id = '92495d52-71b5-560d-71f1-f686e54d8923'
      AND CAST(c.HORA_INICIO AS DATETIME) < CAST('14:00:00' AS DATETIME)
      AND CAST(c.HORA_FINAL AS DATETIME) > CAST('13:00:00' AS DATETIME)
    """, nativeQuery = true)
    boolean checkSolapamientoFijo();
}
