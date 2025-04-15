package com.clinic.ms_citas.service;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas () {
        return citaRepository.getAllCitas();
    }

    public List<Cita> getFilteredCitas(Date dia, Time horaInicio, Time horaFinal, UUID pacienteId, UUID especialistaId, UUID servicioId, Boolean pagado) {
        return citaRepository.getFilteredCitas(dia, horaInicio, horaFinal, pacienteId, especialistaId, servicioId, pagado);
    }

    public List<CitaDTO> getAllCitasDTO () { return citaRepository.getAllCitasDTO(); }

    public void deleteLogicalDeletedCitas() { citaRepository.deleteLogicalDeletedCitas(); }
}
