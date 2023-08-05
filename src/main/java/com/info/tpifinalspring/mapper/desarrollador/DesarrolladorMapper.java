package com.info.tpifinalspring.mapper.desarrollador;

import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;

public interface DesarrolladorMapper {
    Desarrollador desarrolladorDTOtoDesarrollador(DesarrolladorDTO desarrolladorDTO);

    DesarrolladorDTO desarrolladorToDesarrolladorDTO(Desarrollador desarrollador);
}