package com.clinic.ms_citas.client;

import com.clinic.ms_citas.config.GsonConfig;
import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/citas")
public interface ICita {

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas();

    @PostMapping("/calendario")
    public ResponseEntity<List<Cita>> getCitasCalendario(
            @RequestHeader(value = "Tracking-Id") String trackingId,
            @RequestBody Map<String, Object> filtros
    );

    @PostMapping("/filtro")
    public ResponseEntity<List<Cita>> findCitasByFiltro(
            @RequestHeader(value = "Tracking-Id") String trackingId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestBody Map<String, Object> filtros
    );

    @PostMapping("/filtro/total")
    public ResponseEntity<Long> getTotalFiltros(
            @RequestHeader(value = "Tracking-Id") String trackingId,
            @RequestBody Map<String, Object> filtros
    );

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

    @PostMapping("/create")
    public ResponseEntity<Cita> createCita(@RequestBody String jsonCita);

    @PutMapping("/update")
    public ResponseEntity<Cita> updateCita(@RequestBody String jsonCita);

    @PatchMapping("/soft-delete")
    public ResponseEntity<Void> softDeleteCitas(@RequestBody Map<String, Object> citas);

    @DeleteMapping("/delete-logical")
    public ResponseEntity<Void> deleteLogicalDeletedCitas();
}
