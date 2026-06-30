package cl.duoc.itinerario.dto;

import lombok.Data;

@Data

public class TokenValidationResponseDTO {

    private boolean valid;
    private Long usuarioId;
    private String username;

}
