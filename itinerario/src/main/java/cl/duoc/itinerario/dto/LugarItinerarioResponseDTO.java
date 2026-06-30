package cl.duoc.itinerario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LugarItinerarioResponseDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private Integer ordenVisita;
    private String tipoLugar;

}
