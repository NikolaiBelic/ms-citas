package com.clinic.ms_citas.controller;

import com.clinic.ms_citas.client.ICita;
import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Controller
public class CitaController implements ICita {

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.getAllCitas();

        return ResponseEntity.status(HttpStatus.OK).body(citas);
    }

    @Override
    public ResponseEntity<List<Cita>> getFilteredCitas(Date dia, Time horaInicio, Time horaFinal, UUID pacienteId, UUID especialistaId, UUID servicioId, Boolean pagado) {
        List<Cita> citas = citaService.getFilteredCitas(dia, horaInicio, horaFinal, pacienteId, especialistaId, servicioId, pagado);
        return ResponseEntity.status(HttpStatus.OK).body(citas);
    }

    @Override
    public ResponseEntity<List<CitaDTO>> getAllCitasDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getAllCitasDTO());
    }

    @Override
    public ResponseEntity<Void> deleteLogicalDeletedCitas() {
        citaService.deleteLogicalDeletedCitas();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
