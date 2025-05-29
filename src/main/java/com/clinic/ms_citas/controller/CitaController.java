package com.clinic.ms_citas.controller;

import com.clinic.ms_citas.client.ICita;
import com.clinic.ms_citas.config.GsonConfig;
import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.service.CitaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class CitaController implements ICita {

    private final Gson gson = GsonConfig.createGson();

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.getAllCitas();

        return ResponseEntity.status(HttpStatus.OK).body(citas);
    }

    @Override
    public ResponseEntity<List<Cita>> getCitasCalendario(
            String trackingId,
            Map<String, Object> filtros
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getCitasCalendario(
                trackingId,
                filtros
        ));
    }

    @Override
    public ResponseEntity<List<Cita>> findCitasByFiltro(
            String trackingId,
            int page,
            int size,
            Map<String, Object> filtros
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.findCitasByFiltro(
                trackingId,
                page,
                size,
                filtros
        ));
    }

    @Override
    public ResponseEntity<Long> getTotalFiltros(String trackingId, Map<String, Object> filtros) {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getTotalFiltros(trackingId, filtros));
    }

    public ResponseEntity<Cita> createCita(@RequestBody String jsonCita) {
        Cita cita = gson.fromJson(jsonCita, Cita.class);
        Cita c = citaService.createCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    public ResponseEntity<Cita> updateCita(@RequestBody String jsonCita) {
        Cita cita = gson.fromJson(jsonCita, Cita.class);
        Cita c = citaService.updateCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @Override
    public ResponseEntity<Void> softDeleteCitas(Map<String, Object> citas) {
        List<UUID> ids = (List<UUID>) citas.get("ids");
        String deletedBy = (String) citas.get("deletedBy");
        citaService.softDeleteCitas(ids, deletedBy);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Cita>> getFilteredCitas(Date dia, Time horaInicio, Time horaFinal, UUID pacienteId, UUID especialistaId, UUID servicioId, Boolean pagado) {
        List<Cita> citas = citaService.getFilteredCitas(dia, horaInicio, horaFinal, pacienteId, especialistaId, servicioId, pagado);
        return ResponseEntity.status(HttpStatus.OK).body(citas);
    }



    @Override
    public ResponseEntity<Void> deleteLogicalDeletedCitas() {
        citaService.deleteLogicalDeletedCitas();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
