package com.info.tpifinalspring.service.tarea;
import com.info.tpifinalspring.enumeration.EstadoEnum;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TareaService {
    public TareaDTO asignarTareaADesarrollador(UUID desarrolladorUuid, UUID juegoUuid, String descripcion, LocalDate fechaLimite) throws NotFoundException;

    List<TareaDTO> getTareasByDesarrollador(UUID desarrolladorUuid)throws NotFoundException;
    void actualizarEstadoTarea(UUID tareaUuid, EstadoEnum estado) throws NotFoundException;
    List<TareaDTO> buscarTareasPorEstado(EstadoEnum estado);
    List<TareaDTO> buscarTareasPorFechaLimite(LocalDate fechaLimite);
    List<TareaDTO> buscarTareasPorJuego(UUID juegoUuid) throws NotFoundException;
    public boolean existeDesarrollador(UUID desarrolladorUuid);

}
