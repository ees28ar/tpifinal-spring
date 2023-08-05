package com.info.tpifinalspring.repository.juego;
import com.info.tpifinalspring.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
public interface JuegoRepository extends JpaRepository<Juego, UUID> {
    List<Juego> findByTituloEqualsIgnoreCase(String titulo);

    List<Juego> findAllByFechaLanzamientoBefore(LocalDate now);
    boolean existsById(UUID juegoUuid);
}
