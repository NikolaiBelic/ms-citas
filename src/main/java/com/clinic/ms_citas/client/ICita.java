package com.clinic.ms_citas.client;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/citas")
public interface ICita {

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas();

    /**
     *
     * @return
     */
    @GetMapping("/dto")
    public ResponseEntity<List<CitaDTO>> getAllCitasDTO();
}
