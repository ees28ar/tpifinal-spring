package com.info.tpifinalspring.model.dto.tarea;
import com.info.tpifinalspring.enumeration.EstadoEnum;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TareaDTO {
    private UUID tareaUuid;
    private String descripcion;
    private String desarrolladorResponsable;
    private UUID juegoUuid; // UUID del juego asociado a la tarea
    private LocalDate fechaLimite;
    private EstadoEnum estado;
}
