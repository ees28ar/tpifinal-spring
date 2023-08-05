package com.info.tpifinalspring.model.dto.desarrollador;

import com.info.tpifinalspring.enumeration.RolEnum;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesarrolladorDTO {
    private UUID uuid;
    private String nombre;
    private String correo;
    private RolEnum rol;
}

