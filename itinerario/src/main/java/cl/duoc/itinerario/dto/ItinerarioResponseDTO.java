package cl.duoc.itinerario.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder

public class ItinerarioResponseDTO {

    private Long id;
    private Long viajeId;
    private Long usuarioId;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private List<LugarItinerarioResponseDTO> lugares;

}
