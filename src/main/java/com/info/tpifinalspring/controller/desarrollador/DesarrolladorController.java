package com.info.tpifinalspring.controller.desarrollador;

import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorResponse;
import com.info.tpifinalspring.service.desarrollador.DesarrolladorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/desarrolladores")
@RequiredArgsConstructor
public class DesarrolladorController {
    private final DesarrolladorService desarrolladorService;

    @PostMapping
    public ResponseEntity<DesarrolladorDTO> createDesarrollador(@RequestBody DesarrolladorDTO desarrolladorDTO) throws NotFoundException, NotFoundException {
        DesarrolladorDTO createdDesarrollador = desarrolladorService.createDesarrollador(desarrolladorDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/desarrolladores/" + createdDesarrollador.getUuid());

        return new ResponseEntity<>(createdDesarrollador, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DesarrolladorDTO> getAllDesarrolladores() {
        return desarrolladorService.getAllDesarrolladores();
    }

    @GetMapping("/buscar")
    public ResponseEntity<DesarrolladorResponse> buscarDesarrolladoresPorNombre(@RequestParam String nombre) {
        try {
            List<DesarrolladorDTO> desarrolladores = desarrolladorService.buscarDesarrolladoresPorNombre(nombre);
            DesarrolladorResponse response = new DesarrolladorResponse();
            response.setDesarrolladores(desarrolladores);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            DesarrolladorResponse response = new DesarrolladorResponse();
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
