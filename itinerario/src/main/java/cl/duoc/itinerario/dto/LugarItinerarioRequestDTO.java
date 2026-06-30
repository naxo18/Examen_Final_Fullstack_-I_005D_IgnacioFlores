package cl.duoc.itinerario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class LugarItinerarioRequestDTO {

    @NotNull(message = "El tipo de lugar es obligatorio")
    private Long tipoLugarId;

    @NotBlank(message = "El nombre del lugar es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccion;

    @NotNull(message = "El orden de visita es obligatorio")
    @Min(value = 1, message = "El orden de visita debe ser mayor a 0")
    private Integer ordenVisita;

}
