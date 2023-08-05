package com.info.tpifinalspring.mapper.tarea.impl;
import com.info.tpifinalspring.domain.Tarea;
import com.info.tpifinalspring.mapper.desarrollador.DesarrolladorMapper;
import com.info.tpifinalspring.mapper.tarea.TareaMapper;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Component
@RequiredArgsConstructor
public class TareaMapperImpl implements TareaMapper {


    private final DesarrolladorMapper desarrolladorMapper;

    @Override
    public Tarea tareaDTOToTarea(TareaDTO tarea) {
        return Tarea.builder()
               .uuid(UUID.randomUUID())
                .estado(tarea.getEstado())
                .descripcion(tarea.getDescripcion())
                .fechaLimite(tarea.getFechaLimite())
                .build();
    }

    @Override
    public TareaDTO tareaToTareaDTO(Tarea tarea) {
        return TareaDTO.builder()
                .tareaUuid(tarea.getUuid())
                .descripcion(tarea.getDescripcion())
                .desarrolladorResponsable(tarea.getDesarrolladorResponsable().getUuid().toString()) // Cambia a UUID
                .juegoUuid(tarea.getJuego().getUuid()) // relación con Juego
                .fechaLimite(tarea.getFechaLimite())
                .estado(tarea.getEstado())
                .build();
    }
}


