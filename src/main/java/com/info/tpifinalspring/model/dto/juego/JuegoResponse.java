package com.info.tpifinalspring.model.dto.juego;

import com.info.tpifinalspring.model.dto.juego.JuegoDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
public class JuegoResponse {
    private List<JuegoDTO> juegos;
    private String errorMessage;
}

