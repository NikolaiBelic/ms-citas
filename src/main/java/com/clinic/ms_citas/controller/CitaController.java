package com.clinic.ms_citas.controller;

import com.clinic.ms_citas.client.ICita;
import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CitaController implements ICita {

    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<List<Cita>> getAllCitas() {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getAllCitas());
    }

    @Override
    public ResponseEntity<List<CitaDTO>> getAllCitasDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getAllCitasDTO());
    }
}
