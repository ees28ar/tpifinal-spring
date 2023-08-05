package com.info.tpifinalspring.mapper.juego;

import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;

public interface JuegoMapper {

    Juego juegoDTOtoJuego(JuegoDTO juegoDTO);
    JuegoDTO juegoToJuegoDTO(Juego juego);


}
