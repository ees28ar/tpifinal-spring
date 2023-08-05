package com.info.tpifinalspring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Juego {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @JdbcTypeCode(SqlTypes.CHAR)
        @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
        private UUID uuid;

        @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
        private String titulo;

        @Column(length = 500, columnDefinition = "varchar(500)", updatable = true, nullable = false)
        private String descripcion;

        private LocalDate fechaLanzamiento;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "desarrollador_uuid")
    private Desarrollador desarrollador; // referencia directa al Desarrollador responsable del juego

        public void setDesarrollador(Desarrollador desarrollador) {
            this.desarrollador = desarrollador;
        }
    }

