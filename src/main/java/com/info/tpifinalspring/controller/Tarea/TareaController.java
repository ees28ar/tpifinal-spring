package com.info.tpifinalspring.controller.Tarea;

import com.info.tpifinalspring.enumeration.EstadoEnum;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.mapper.tarea.TareaMapper;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;
import com.info.tpifinalspring.model.dto.tarea.TareaResponse;
import com.info.tpifinalspring.service.tarea.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {
    private final TareaService tareaService;
    private final TareaMapper tareaMapper;

    //realiza la solicitud POST al endpoint
    @PostMapping("/juego/{juegoUuid}/desarrollador/{desarrolladorUuid}")
    //TareaController recibe la solicitud para asignar la tarea en el método asignarTareaADesarrollador().
    // Id de juego y desarrollador a travez de la Variable y el resto a traves del RequestBody
    public ResponseEntity<TareaResponse> asignarTareaADesarrollador(
            @PathVariable UUID juegoUuid,
            @PathVariable UUID desarrolladorUuid,
            @RequestBody TareaDTO tareaDTO
            //recibe un objeto TareaDTO en el cuerpo de la solicitud.
    ) {

        // instancia de TareaResponse, contiene una lista de tareas y un mensaje de error (String errorMessage).
        TareaResponse response = new TareaResponse();
        try {
            //invocaciòn a Tarea Service
            TareaDTO createdTarea = tareaService.asignarTareaADesarrollador(desarrolladorUuid, juegoUuid, tareaDTO.getDescripcion(), tareaDTO.getFechaLimite());
            response.setTareas(Collections.singletonList(createdTarea));
        } catch (NotFoundException e) {
            response.setErrorMessage("Error: " + e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/desarrollador/{desarrolladorUuid}")
    public List<TareaDTO> getTareasByDesarrollador(@PathVariable UUID desarrolladorUuid) throws NotFoundException {
        // Valida que el desarrollador exista antes de buscar su tarea
        if (!tareaService.existeDesarrollador(desarrolladorUuid)) {
            throw new NotFoundException("Desarrollador not found with ID: " + desarrolladorUuid);
        }
        return tareaService.getTareasByDesarrollador(desarrolladorUuid);
    }

    @PutMapping("/{tareaUuid}/estado/{estado}")
    public ResponseEntity<Void> actualizarEstadoTarea(@PathVariable UUID tareaUuid, @PathVariable EstadoEnum estado) {
        try {
            tareaService.actualizarEstadoTarea(tareaUuid, estado);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<TareaResponse> buscarTareasPorEstado(@PathVariable EstadoEnum estado) {
        List<TareaDTO> tareas = tareaService.buscarTareasPorEstado(estado);
        TareaResponse response = new TareaResponse();

        if (tareas.isEmpty()) {
            response.setErrorMessage("No se encontraron tareas con el estado: " + estado);
        } else {
            response.setTareas(tareas);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/fechaLimite/{fechaLimite}")
    public List<TareaDTO> buscarTareasPorFechaLimite(@PathVariable LocalDate fechaLimite) {
        return tareaService.buscarTareasPorFechaLimite(fechaLimite);
    }

    @GetMapping("/juego/{juegoUuid}")
    public ResponseEntity<TareaResponse> buscarTareasPorJuego(@PathVariable UUID juegoUuid) {
        try {
            List<TareaDTO> tareas = tareaService.buscarTareasPorJuego(juegoUuid);
            TareaResponse response = new TareaResponse();

            if (tareas.isEmpty()) {
                response.setErrorMessage("No se encontraron tareas para el juego con ID: " + juegoUuid);
            } else {
                response.setTareas(tareas);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            TareaResponse response = new TareaResponse();
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vencidas")
    public List<TareaDTO> buscarTareasVencidas() {
        return tareaService.buscarTareasPorFechaLimite(LocalDate.now());
    }
}