package com.clinic.ms_citas.client;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@RequestMapping("/citas")
public interface ICita {

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas();

    @GetMapping("/filtradas")
    public ResponseEntity<List<Cita>> getFilteredCitas(
            @RequestParam(value = "dia", required = false) Date dia,
            @RequestParam(value = "horaInicio", required = false) Time horaInicio,
            @RequestParam(value = "horaFinal", required = false) Time horaFinal,
            @RequestParam(value = "pacienteId", required = false) UUID pacienteId,
            @RequestParam(value = "especialistaId", required = false) UUID especialistaId,
            @RequestParam(value = "servicioId", required = false) UUID servicioId,
            @RequestParam(value = "pagado", required = false) Boolean pagado
    );

    /**
     *
     * @return
     */
    @GetMapping("/dto")
    public ResponseEntity<List<CitaDTO>> getAllCitasDTO();

    @DeleteMapping("/delete-logical")
    public ResponseEntity<Void> deleteLogicalDeletedCitas();
}
