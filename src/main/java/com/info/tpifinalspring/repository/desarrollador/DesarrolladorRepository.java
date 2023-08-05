package com.info.tpifinalspring.repository.desarrollador;
import com.info.tpifinalspring.domain.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface DesarrolladorRepository extends JpaRepository<Desarrollador, UUID> {

    List<Desarrollador> findByJuego_Uuid(UUID juegoUuid);
    List<Desarrollador> findByNombreIgnoreCase(String nombre);


}


