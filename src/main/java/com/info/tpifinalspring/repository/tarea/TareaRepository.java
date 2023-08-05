package com.info.tpifinalspring.repository.tarea;
import com.info.tpifinalspring.domain.Tarea;
import com.info.tpifinalspring.enumeration.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {
    List<Tarea> findAllByEstado(EstadoEnum estado);
    List<Tarea> findAllByFechaLimite(LocalDate fechaLimite);
    List<Tarea> findAllByJuegoUuid(UUID juegoUuid);
    List<Tarea> findAllByDesarrolladorResponsableUuid(UUID desarrolladorUuid);
}