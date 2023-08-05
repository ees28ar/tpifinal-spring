package com.info.tpifinalspring.mapper.tarea;

import com.info.tpifinalspring.domain.Tarea;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;

public interface TareaMapper {

    Tarea tareaDTOToTarea(TareaDTO tarea);
    TareaDTO tareaToTareaDTO (Tarea tarea);

}
