package com.info.tpifinalspring.mapper.desarrollador.impl;
import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.mapper.desarrollador.DesarrolladorMapper;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;
import org.springframework.stereotype.Component;


@Component
public class DesarrolladorMapperImpl implements DesarrolladorMapper {
    @Override
    public final DesarrolladorDTO desarrolladorToDesarrolladorDTO(Desarrollador desarrollador) {
        return DesarrolladorDTO.builder()
                .uuid(desarrollador.getUuid())
                .nombre(desarrollador.getNombre())
                .correo(desarrollador.getCorreo())
                .rol(desarrollador.getRol())
                .build();
    }

    @Override
    public final Desarrollador desarrolladorDTOtoDesarrollador(DesarrolladorDTO desarrolladorDTO) {
        return Desarrollador.builder()
                .uuid(desarrolladorDTO.getUuid())
                .nombre(desarrolladorDTO.getNombre())
                .correo(desarrolladorDTO.getCorreo())
                .rol(desarrolladorDTO.getRol())
                .build();
    }
}

