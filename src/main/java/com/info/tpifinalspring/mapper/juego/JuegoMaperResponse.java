package com.info.tpifinalspring.mapper.juego;

import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;


public interface JuegoMaperResponse {
    JuegoDTO bookToBookResponseDTO(Juego juego);
}
