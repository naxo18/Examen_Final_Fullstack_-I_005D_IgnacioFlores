package cl.duoc.itinerario.repository;

import cl.duoc.itinerario.model.TipoLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoLugarRepository extends JpaRepository<TipoLugar, Long> {
    
    Optional<TipoLugar> findByNombreIgnoreCase(String nombre);
}