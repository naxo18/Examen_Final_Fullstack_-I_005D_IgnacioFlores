package cl.duoc.itinerario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class ItinerarioRequestDTO {

     @NotNull(message = "El viaje es obligatorio")
    private Long viajeId;

    @NotBlank(message = "El nombre del itinerario es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

}
