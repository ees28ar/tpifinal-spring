package com.info.tpifinalspring.service.tarea.impl;

import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.domain.Tarea;
import com.info.tpifinalspring.enumeration.EstadoEnum;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.mapper.tarea.TareaMapper;
import com.info.tpifinalspring.model.dto.tarea.TareaDTO;
import com.info.tpifinalspring.repository.desarrollador.DesarrolladorRepository;
import com.info.tpifinalspring.repository.juego.JuegoRepository;
import com.info.tpifinalspring.repository.tarea.TareaRepository;
import com.info.tpifinalspring.service.tarea.TareaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;
    private final TareaMapper tareaMapper;
    DesarrolladorRepository desarrolladorRepository;
    private final JuegoRepository juegoRepository;
    @Override
    public TareaDTO asignarTareaADesarrollador(UUID desarrolladorUuid, UUID juegoUuid, String descripcion, LocalDate fechaLimite) throws NotFoundException {
        //Verifica si el desarrollador esta Caragado en la base datos
        if (!existeDesarrollador(desarrolladorUuid)) {
            throw new NotFoundException("Desarrollador not found with ID: " + desarrolladorUuid);
        }

        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorUuid)
                .orElseThrow(() -> new NotFoundException("Desarrollador not found with ID: " + desarrolladorUuid));
        ///si el desarrollador existe se obtiene el Juego asociado
        Juego juegoAsociado = desarrollador.getJuego();

        // Verificar si el juego asociado existe antes de continuar
        if (juegoAsociado == null) {
            throw new NotFoundException("No hay juego asociado al desarrollador con ID: " + desarrolladorUuid);
        }
        // verifica que el juego brindado en la solicitud, también exista en la base de datos de Juegos
        Juego juegoSeleccionado = juegoRepository.findById(juegoUuid)
                .orElseThrow(() -> new NotFoundException("Juego no encontrado con ID: " + juegoUuid));

        //Compara el juego seleccionado (juegoSeleccionado) con el juego asociado al desarrollador (juegoAsociado)
        if (!juegoSeleccionado.equals(juegoAsociado)) {
            throw new NotFoundException("El juego con ID: " + juegoUuid + " no está asociado al desarrollador con ID: " + desarrolladorUuid);
        }

        Tarea tarea = Tarea.builder()
                .descripcion(descripcion)
                .fechaLimite(fechaLimite)
                .juego(juegoAsociado)
                .desarrolladorResponsable(desarrollador)
                .estado(EstadoEnum.PENDIENTE) // Estado por defecto al crear la tarea
                .build();

        tareaRepository.save(tarea);

        // Devulve la TareaDTO con el UUID asignado
        return tareaMapper.tareaToTareaDTO(tarea);
    }

    @Override
    public List<TareaDTO> getTareasByDesarrollador(UUID desarrolladorUuid) throws NotFoundException {
        if (!existeDesarrollador(desarrolladorUuid)) {
            throw new NotFoundException("Desarrollador no encontrado con ID: " + desarrolladorUuid);
        }
        return tareaRepository.findAllByDesarrolladorResponsableUuid(desarrolladorUuid)
                .stream()
                .map(tareaMapper::tareaToTareaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarEstadoTarea(UUID tareaUuid, EstadoEnum estado) throws NotFoundException {
        Tarea tarea = tareaRepository.findById(tareaUuid)
                .orElseThrow(() -> new NotFoundException("Tarea no encontrada con ID: " + tareaUuid));

        tarea.setEstado(estado);
        tareaRepository.save(tarea);
    }

    @Override
    public List<TareaDTO> buscarTareasPorEstado(EstadoEnum estado) {
        List<Tarea> tareas = tareaRepository.findAllByEstado(estado);
        return tareas.stream().map(tareaMapper::tareaToTareaDTO).collect(Collectors.toList());
    }


    @Override
    public List<TareaDTO> buscarTareasPorFechaLimite(LocalDate fechaLimite) {
        return tareaRepository.findAllByFechaLimite(fechaLimite)
                .stream()
                .map(tareaMapper::tareaToTareaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> buscarTareasPorJuego(UUID juegoUuid) throws NotFoundException {
        // Verificar si el juego existe antes de buscar las tareas
        if (!juegoRepository.existsById(juegoUuid)) {
            throw new NotFoundException("Juego no encontrado con ID: " + juegoUuid);
        }

        return tareaRepository.findAllByJuegoUuid(juegoUuid)
                .stream()
                .map(tareaMapper::tareaToTareaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeDesarrollador(UUID desarrolladorUuid) {
        return desarrolladorRepository.existsById(desarrolladorUuid);
    }

}
