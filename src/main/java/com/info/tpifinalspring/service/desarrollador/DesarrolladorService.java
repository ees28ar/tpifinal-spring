package com.info.tpifinalspring.service.desarrollador;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;
import java.util.List;
import java.util.UUID;

public interface DesarrolladorService {
    DesarrolladorDTO createDesarrollador(DesarrolladorDTO desarrolladorDTO) throws NotFoundException;
    List<DesarrolladorDTO> getDesarrolladoresByJuego(UUID juegoUuid);

    List<DesarrolladorDTO> getAllDesarrolladores();
    List<DesarrolladorDTO> buscarDesarrolladoresPorNombre(String nombre) throws NotFoundException;
}