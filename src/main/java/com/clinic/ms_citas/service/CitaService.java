package com.clinic.ms_citas.service;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas () {
        return citaRepository.getAllCitas();
    }

    public List<CitaDTO> getAllCitasDTO () {
        return citaRepository.getAllCitasDTO();
    }
}
