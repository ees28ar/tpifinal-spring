package com.info.tpifinalspring.controller.juego;

import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;
import com.info.tpifinalspring.model.dto.juego.JuegoResponse;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;
import com.info.tpifinalspring.service.desarrollador.DesarrolladorService;
import com.info.tpifinalspring.service.juego.JuegoService;
import com.info.tpifinalspring.service.tarea.TareaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/v1/juegos")
@RequiredArgsConstructor
public class JuegoController {
    @Autowired
    private final JuegoService juegoService;
    private final DesarrolladorService desarrolladorService;
    private final TareaService tareaService;

    @GetMapping
    public List<JuegoDTO> getAllJuegos() {
        return juegoService.getAllJuegos();
    }

    @GetMapping("/buscar")
    public ResponseEntity<JuegoResponse> buscarJuegosPorNombre(@RequestParam(name = "nombre", required = true) String nombre) {
        try {
            List<JuegoDTO> juegosPorNombre = juegoService.buscarJuegosPorNombre(nombre);
            JuegoResponse response = new JuegoResponse();
            response.setJuegos(juegosPorNombre);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            JuegoResponse response = new JuegoResponse();
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity createJuego(@RequestBody JuegoDTO juego) throws NotFoundException {
        Juego createdJuego = juegoService.createJuego(juego); // Llama al método con JuegoDTO
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/juegos/" + createdJuego.getUuid());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/finalizados")
    public ResponseEntity<JuegoResponse> getJuegosFinalizados() {
        try {
            List<JuegoDTO> juegosFinalizados = juegoService.getJuegosFinalizados();
            JuegoResponse response = new JuegoResponse();
            response.setJuegos(juegosFinalizados);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            JuegoResponse response = new JuegoResponse();
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idJuegos}/desarrolladores")
    public List<DesarrolladorDTO> getDesarrolladoresByJuego(@PathVariable(value = "idJuegos") UUID juegoUuid) {
        return desarrolladorService.getDesarrolladoresByJuego(juegoUuid);
    }

    @GetMapping("/{idJuegos}")
    public ResponseEntity<?> getJuegoById(@PathVariable(value = "idJuegos") UUID idJuegos) {
        Optional<JuegoDTO> juegoOptional = juegoService.getJuegosById(idJuegos);
        if (juegoOptional.isPresent()) {
            return new ResponseEntity<>(juegoOptional.get(), HttpStatus.OK);
        } else {
            String errorMessage = "Juego no encontrado con ID: " + idJuegos;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{juegoUuid}/desarrollador/{desarrolladorUuid}")
    public ResponseEntity<JuegoResponse> asignarDesarrolladorAJuego(@PathVariable UUID juegoUuid, @PathVariable UUID desarrolladorUuid) {
        try {
            juegoService.asignarDesarrolladorAJuego(juegoUuid, desarrolladorUuid);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            JuegoResponse response = new JuegoResponse();
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}