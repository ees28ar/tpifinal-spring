package com.info.tpifinalspring.mapper.juego.impl;
import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.mapper.desarrollador.DesarrolladorMapper;
import com.info.tpifinalspring.mapper.juego.JuegoMapper;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JuegoMapperImpl implements JuegoMapper {

    private final DesarrolladorMapper desarrolladorMapper;
    @Override
    public Juego juegoDTOtoJuego(JuegoDTO juegoDTO) {
        return Juego.builder()
                .uuid(UUID.randomUUID())
                .titulo(juegoDTO.getTitulo())
                .descripcion(juegoDTO.getDescripcion())
                .fechaLanzamiento(juegoDTO.getFechaLanzamiento())
                .build();
    }



    @Override
    public JuegoDTO juegoToJuegoDTO(Juego juego) {
        return JuegoDTO.builder()
                .uuid(juego.getUuid())
                .titulo(juego.getTitulo())
                .descripcion(juego.getDescripcion())
                .fechaLanzamiento(juego.getFechaLanzamiento())
                .build();
    }
    }

