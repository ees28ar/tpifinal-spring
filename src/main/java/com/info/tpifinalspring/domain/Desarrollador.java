package com.info.tpifinalspring.domain;


import com.info.tpifinalspring.enumeration.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Desarrollador {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 50, columnDefinition = "varchar(50)", updatable = true, nullable = false)
    private String nombre;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
    private String correo;

    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    // Relación Many-to-One con Juego
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "juego_uuid")
    private Juego juego; //  juego asociado al desarrollador
}
