package cl.duoc.itinerario.controller;

import cl.duoc.itinerario.dto.ApiResponse;
import cl.duoc.itinerario.dto.TipoLugarResponseDTO;
import cl.duoc.itinerario.service.TipoLugarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Tipos de Lugar", description = "Catálogo de tipos de lugar para el itinerario")
@RestController
@RequestMapping("/api/v1/itinerary/tipos-lugar")
@RequiredArgsConstructor

public class TipoLugarController {

    

}
