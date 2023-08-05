package com.info.tpifinalspring.service.juego;
import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JuegoService {

    List<JuegoDTO> getAllJuegos();

    Juego createJuego(JuegoDTO juego) throws NotFoundException;

    Optional<JuegoDTO> getJuegosById(UUID uuid);

    List<JuegoDTO> getJuegosFinalizados() throws NotFoundException;

    void asignarDesarrolladorAJuego(UUID juegoUuid, UUID desarrolladorUuid) throws NotFoundException;

    List<JuegoDTO> buscarJuegosPorNombre(String nombre) throws NotFoundException;
}

