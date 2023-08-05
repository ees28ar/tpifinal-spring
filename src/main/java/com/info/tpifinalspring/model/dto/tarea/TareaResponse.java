package com.info.tpifinalspring.model.dto.tarea;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@RequiredArgsConstructor
@Getter
@Setter
public class TareaResponse {
        private List<TareaDTO> tareas;
        private String errorMessage;
}
