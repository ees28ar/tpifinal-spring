package com.info.tpifinalspring.service.desarrollador.impl;
import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.mapper.desarrollador.DesarrolladorMapper;
import com.info.tpifinalspring.model.dto.desarrollador.DesarrolladorDTO;
import com.info.tpifinalspring.repository.desarrollador.DesarrolladorRepository;
import com.info.tpifinalspring.service.desarrollador.DesarrolladorService;
import com.info.tpifinalspring.service.tarea.TareaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DesarrolladorServiceImpl implements DesarrolladorService {
    //inyectando metodos
    private final DesarrolladorRepository desarrolladorRepository;
    private final DesarrolladorMapper desarrolladorMapper;
    private final TareaService tareaService;

    @Override
    public DesarrolladorDTO createDesarrollador(DesarrolladorDTO desarrolladorDTO) {
        Desarrollador desarrollador = desarrolladorMapper.desarrolladorDTOtoDesarrollador(desarrolladorDTO);
        Desarrollador createdDesarrollador = desarrolladorRepository.save(desarrollador);
        return desarrolladorMapper.desarrolladorToDesarrolladorDTO(createdDesarrollador);
    }

    @Override
    public List<DesarrolladorDTO> getDesarrolladoresByJuego(UUID juegoUuid) {
        List<Desarrollador> desarrolladores = desarrolladorRepository.findByJuego_Uuid(juegoUuid);
        return desarrolladores.stream()
                .map(desarrolladorMapper::desarrolladorToDesarrolladorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DesarrolladorDTO> buscarDesarrolladoresPorNombre(String nombre) throws NotFoundException {
        List<Desarrollador> desarrolladores = desarrolladorRepository.findByNombreIgnoreCase(nombre);

        if (desarrolladores.isEmpty()) {
            throw new NotFoundException("No se encontraron desarrolladores con el nombre: " + nombre);
        }

        return desarrolladores.stream()
                .map(desarrolladorMapper::desarrolladorToDesarrolladorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DesarrolladorDTO> getAllDesarrolladores() {
        List<Desarrollador> desarrolladores = desarrolladorRepository.findAll();
        return desarrolladores.stream()
                .map(desarrolladorMapper::desarrolladorToDesarrolladorDTO)
                .collect(Collectors.toList());
    }

}




