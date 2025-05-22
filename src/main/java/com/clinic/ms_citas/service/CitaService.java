package com.clinic.ms_citas.service;

import com.clinic.ms_citas.dto.CitaDTO;
import com.clinic.ms_citas.model.Cita;
import com.clinic.ms_citas.model.Especialista;
import com.clinic.ms_citas.model.Servicio;
import com.clinic.ms_citas.model.paciente.Paciente;
import com.clinic.ms_citas.repository.CitaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.clinic.ms_citas.utils.DateFormat.ajustarFechaAEspana;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cita> getAllCitas() {
        List<Cita> citas = citaRepository.getAllCitas();

        // Inicializar relaciones lazy
        citas.forEach(cita -> {
            if (cita.getPaciente() != null) {
                Hibernate.initialize(cita.getPaciente());
            }
            if (cita.getEspecialista() != null) {
                Hibernate.initialize(cita.getEspecialista());
            }
            if (cita.getServicio() != null) {
                Hibernate.initialize(cita.getServicio());
            }
        });

        return citas;
    }

    public List<Cita> findCitasByFiltro(String trackingId, int page, int size, Map<String, Object> filtros) {
        StringBuilder sql = new StringBuilder(
                "SELECT c.* " +
                        "FROM CLINIC_CITA c " +
                        "LEFT JOIN CLINIC_ESPECIALISTA e ON c.ESPECIALISTA_ID = e.ID " +
                        "LEFT JOIN CLINIC_PACIENTE p ON c.PACIENTE_ID = p.ID " +
                        "LEFT JOIN CLINIC_SERVICIO s ON c.SERVICIO_ID = s.ID " +
                        "WHERE 1 = 1 AND c.DELETE_TS IS NULL");

        Map<String, Object> paramsQuery = new HashMap<>();

        if (filtros.containsKey("dia")) {
            sql.append(" AND c.DIA = :dia");
            paramsQuery.put("dia", new java.sql.Date((Long) filtros.get("dia")));
        }

        if (filtros.containsKey("horaInicio")) {
            sql.append(" AND c.HORA_INICIO = :horaInicio");
            paramsQuery.put("horaInicio", filtros.get("horaInicio"));
        }

        if (filtros.containsKey("horaFinal")) {
            sql.append(" AND c.HORA_FINAL = :horaFinal");
            paramsQuery.put("horaFinal", filtros.get("horaFinal"));
        }

        if (filtros.containsKey("paciente")) {
            sql.append(" AND c.PACIENTE_ID = :paciente");
            paramsQuery.put("paciente", filtros.get("paciente"));
        }

        if (filtros.containsKey("especialista")) {
            sql.append(" AND c.ESPECIALISTA_ID = :especialista");
            paramsQuery.put("especialista", filtros.get("especialista"));
        }

        if (filtros.containsKey("servicio")) {
            sql.append(" AND c.SERVICIO_ID = :servicio");
            paramsQuery.put("servicio", filtros.get("servicio"));
        }

        if (filtros.containsKey("pagado")) {
            sql.append(" AND c.PAGADO = :pagado");
            paramsQuery.put("pagado", filtros.get("pagado"));
        }

        sql.append(" ORDER BY c.ID");
        sql.append(" OFFSET :page ROWS FETCH NEXT :size ROWS ONLY");

        Query query = entityManager.createNativeQuery(sql.toString(), Cita.class);
        query.setParameter("page", page);
        query.setParameter("size", size);
        paramsQuery.forEach(query::setParameter);

        System.out.println(sql.toString());

        List<Cita> citas = query.getResultList();

        // Inicializar relaciones lazy
        citas.forEach(cita -> {
            Hibernate.initialize(cita.getPaciente());
            Hibernate.initialize(cita.getEspecialista());
            Hibernate.initialize(cita.getServicio());
        });

        return citas;
    }

    public Long getTotalFiltros(String trackingId, Map<String, Object> filtros) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) " +
                        "FROM CLINIC_CITA c " +
                        "LEFT JOIN CLINIC_ESPECIALISTA e ON c.ESPECIALISTA_ID = e.ID " +
                        "LEFT JOIN CLINIC_PACIENTE p ON c.PACIENTE_ID = p.ID " +
                        "LEFT JOIN CLINIC_SERVICIO s ON c.SERVICIO_ID = s.ID " +
                        "WHERE 1 = 1 AND c.DELETE_TS IS NULL");

        Map<String, Object> paramsQuery = new HashMap<>();

        if (filtros.containsKey("dia")) {
            sql.append(" AND c.DIA = :dia");
            paramsQuery.put("dia", new java.sql.Date((Long) filtros.get("dia")));
        }

        if (filtros.containsKey("horaInicio")) {
            sql.append(" AND c.HORA_INICIO = :horaInicio");
            paramsQuery.put("horaInicio", filtros.get("horaInicio"));
        }

        if (filtros.containsKey("horaFinal")) {
            sql.append(" AND c.HORA_FINAL = :horaFinal");
            paramsQuery.put("horaFinal", filtros.get("horaFinal"));
        }

        if (filtros.containsKey("paciente")) {
            sql.append(" AND c.PACIENTE_ID = :paciente");
            paramsQuery.put("paciente", filtros.get("paciente"));
        }

        if (filtros.containsKey("especialista")) {
            sql.append(" AND c.ESPECIALISTA_ID = :especialista");
            paramsQuery.put("especialista", filtros.get("especialista"));
        }

        if (filtros.containsKey("servicio")) {
            sql.append(" AND c.SERVICIO_ID = :servicio");
            paramsQuery.put("servicio", filtros.get("servicio"));
        }

        if (filtros.containsKey("pagado")) {
            sql.append(" AND c.PAGADO = :pagado");
            paramsQuery.put("pagado", filtros.get("pagado"));
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Long.class);
        paramsQuery.forEach(query::setParameter);

        System.out.println(sql.toString());

        return (Long) query.getSingleResult();
    }

    @Transactional
    public Cita createCita(Cita cita) {


        if (cita.getPaciente() != null) {
            if (cita.getPaciente().getId() == null) {
                throw new IllegalArgumentException("El paciente debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el paciente existente
            cita.setPaciente(entityManager.getReference(Paciente.class, cita.getPaciente().getId()));
        }

        if (cita.getEspecialista() != null) {
            if (cita.getEspecialista().getId() == null) {
                throw new IllegalArgumentException("El especialista debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el especialista existente
            cita.setEspecialista(entityManager.getReference(Especialista.class, cita.getEspecialista().getId()));
        }

        if (cita.getServicio() != null) {
            if (cita.getServicio().getId() == null) {
                throw new IllegalArgumentException("El servicio debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el servicio existente
            cita.setServicio(entityManager.getReference(Servicio.class, cita.getServicio().getId()));
        }

        if (cita.getHoraInicio() != null) {
            cita.setHoraInicio(normalizeTime(cita.getHoraInicio()));
        }
        if (cita.getHoraFinal() != null) {
            cita.setHoraFinal(normalizeTime(cita.getHoraFinal()));
            System.out.println(normalizeTime(cita.getHoraFinal()));
        }


        cita.setCreateTs(ajustarFechaAEspana(cita.getCreateTs()));
        cita.setUpdateTs(ajustarFechaAEspana(cita.getUpdateTs()));

        return citaRepository.save(cita);
    }

    @Transactional
    public Cita updateCita(Cita cita) {
        if (cita.getPaciente() != null) {
            if (cita.getPaciente().getId() == null) {
                throw new IllegalArgumentException("El paciente debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el paciente existente
            cita.setPaciente(entityManager.getReference(Paciente.class, cita.getPaciente().getId()));
        }

        if (cita.getEspecialista() != null) {
            if (cita.getEspecialista().getId() == null) {
                throw new IllegalArgumentException("El especialista debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el especialista existente
            cita.setEspecialista(entityManager.getReference(Especialista.class, cita.getEspecialista().getId()));
        }

        if (cita.getServicio() != null) {
            if (cita.getServicio().getId() == null) {
                throw new IllegalArgumentException("El servicio debe tener un ID válido para ser vinculado a la cita.");
            }
            // Asociar el servicio existente
            cita.setServicio(entityManager.getReference(Servicio.class, cita.getServicio().getId()));
        }

        if (cita.getHoraInicio() != null) {
            cita.setHoraInicio(normalizeTime(cita.getHoraInicio()));
        }
        if (cita.getHoraFinal() != null) {
            cita.setHoraFinal(normalizeTime(cita.getHoraFinal()));
            System.out.println(normalizeTime(cita.getHoraFinal()));
        }

        cita.setUpdateTs(ajustarFechaAEspana(cita.getUpdateTs()));

        return citaRepository.save(cita);
    }

    public void softDeleteCitas(List<UUID> ids, String deletedBy) {
        java.util.Date deleteTs = new java.util.Date();
        citaRepository.softDeleteCitas(ids, deleteTs, deletedBy);
    }

    public List<Cita> getFilteredCitas(Date dia, Time horaInicio, Time horaFinal, UUID pacienteId, UUID especialistaId, UUID servicioId, Boolean pagado) {
        return citaRepository.getFilteredCitas(dia, horaInicio, horaFinal, pacienteId, especialistaId, servicioId, pagado);
    }

    public void deleteLogicalDeletedCitas() { citaRepository.deleteLogicalDeletedCitas(); }

    private Time normalizeTime(Time time) {
        // Convertir a formato HH:mm:ss.0000000
        return Time.valueOf(time.toLocalTime().withNano(0));
    }
}
