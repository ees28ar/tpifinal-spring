package com.info.tpifinalspring.service.juego.impl;
import com.info.tpifinalspring.domain.Desarrollador;
import com.info.tpifinalspring.domain.Juego;
import com.info.tpifinalspring.exceptions.NotFoundException;
import com.info.tpifinalspring.mapper.juego.JuegoMapper;
import com.info.tpifinalspring.model.dto.juego.JuegoDTO;
import com.info.tpifinalspring.repository.desarrollador.DesarrolladorRepository;
import com.info.tpifinalspring.repository.juego.JuegoRepository;
import com.info.tpifinalspring.service.juego.JuegoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional
public class JuegoServiceImpl implements JuegoService {
    private final JuegoRepository juegoRepository;
    private final JuegoMapper juegoMapper;
    private final DesarrolladorRepository desarrolladorRepository;



    @Override
    public List<JuegoDTO> buscarJuegosPorNombre(String nombre) throws NotFoundException {
        List<Juego> juegos = juegoRepository.findByTituloEqualsIgnoreCase(nombre);

        if (juegos.isEmpty()) {
            throw new NotFoundException("No se encontraron juegos con el nombre: " + nombre);
        }

        return juegos.stream()
                .map(juegoMapper::juegoToJuegoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JuegoDTO> getAllJuegos() {
        return juegoRepository.findAll().stream()
                .map(juego -> {
                    JuegoDTO juegoDTO = juegoMapper.juegoToJuegoDTO(juego);
                    juegoDTO.setUuid(juego.getUuid());
                    return juegoDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Juego createJuego(JuegoDTO juegoDTO) {
        Juego newJuego = juegoMapper.juegoDTOtoJuego(juegoDTO); // Convierte el JuegoDTO a Juego
        return juegoRepository.save(newJuego);
    }

    @Override
    public Optional<JuegoDTO> getJuegosById(UUID uuid) {
        Optional<Juego> juegoOptional = juegoRepository.findById(uuid);
        return juegoOptional.map(juegoMapper::juegoToJuegoDTO);
    }


    @Override
    public List<JuegoDTO> getJuegosFinalizados() throws NotFoundException {
        LocalDate currentDate = LocalDate.now();
        List<Juego> juegosFinalizados = juegoRepository.findAllByFechaLanzamientoBefore(currentDate);

        // Verificar si hay juegos finalizados disponibles
        if (juegosFinalizados.isEmpty()) {
            // Si no hay juegos finalizados, lanzar la excepción NotFound
            throw new NotFoundException("No se encontraron juegos finalizados");
        } else {
            // Si hay juegos finalizados, mapearlos a DTO y devolver la lista de JuegoDTO
            return juegosFinalizados.stream()
                    .map(juegoMapper::juegoToJuegoDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void asignarDesarrolladorAJuego(UUID juegoUuid, UUID desarrolladorUuid) throws NotFoundException {
        Juego juego = juegoRepository.findById(juegoUuid)
                .orElseThrow(() -> new NotFoundException("Juego no encontrado con ID: " + juegoUuid));

        Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorUuid)
                .orElseThrow(() -> new NotFoundException("Desarrollador no encontrado con ID: " + desarrolladorUuid));

        // Verificar si el juego y el desarrollador existen antes de asignarlos
        if (juego == null) {
            throw new NotFoundException("Juego no encontrado con ID: " + juegoUuid);
        }

        if (desarrollador == null) {
            throw new NotFoundException("Desarrollador no encontrado con ID: " + desarrolladorUuid);
        }

        juego.setDesarrollador(desarrollador);
        desarrollador.setJuego(juego);
        juegoRepository.save(juego);
    }
}
