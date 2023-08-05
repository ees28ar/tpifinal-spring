package com.info.tpifinalspring.model.dto.juego;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JuegoDTO {
    private UUID uuid;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLanzamiento;
}