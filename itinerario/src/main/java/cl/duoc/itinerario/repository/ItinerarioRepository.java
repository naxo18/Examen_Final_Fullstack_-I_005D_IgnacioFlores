package cl.duoc.itinerario.repository;

import cl.duoc.itinerario.model.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    List<Itinerario>findByViajeId(Long viajeId);
    List<Itinerario>findByUsuarioId(Long usuarioId);
}
