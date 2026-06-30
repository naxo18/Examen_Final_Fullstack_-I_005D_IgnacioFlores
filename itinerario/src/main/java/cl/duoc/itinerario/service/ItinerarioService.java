package cl.duoc.itinerario.service;

import cl.duoc.itinerario.client.AuthServiceClient;
import cl.duoc.itinerario.client.TravelerServiceClient;
import cl.duoc.itinerario.dto.*;
import cl.duoc.itinerario.exception.ResourceNotFoundException;
import cl.duoc.itinerario.model.Itinerario;
import cl.duoc.itinerario.model.LugarItinerario;
import cl.duoc.itinerario.model.TipoLugar;
import cl.duoc.itinerario.repository.ItinerarioRepository;
import cl.duoc.itinerario.repository.LugarItinerarioRepository;
import cl.duoc.itinerario.repository.TipoLugarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class ItinerarioService {

     private final ItinerarioRepository itinerarioRepository;
    private final LugarItinerarioRepository lugarItinerarioRepository;
    private final TipoLugarRepository tipoLugarRepository;
    private final AuthServiceClient authServiceClient;
    private final TravelerServiceClient travelerServiceClient;

    @Transactional(readOnly = true)
    public List<ItinerarioResponseDTO> listarTodos() {
        return itinerarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItinerarioResponseDTO obtenerPorId(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerario no encontrado con id: " + id));
        return toResponseDTO(itinerario);
    }

    @Transactional(readOnly = true)
    public List<ItinerarioResponseDTO> listarPorViaje(Long viajeId) {
        return itinerarioRepository.findByViajeId(viajeId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public ItinerarioResponseDTO crear(ItinerarioRequestDTO dto, String token) {
        log.info("Validando token con Login Service...");
        TokenValidationResponseDTO usuario = authServiceClient.validarToken(token);

        log.info("Validando existencia del viaje {} en Traveler Service...", dto.getViajeId());
        ViajeResponseDTO viaje = travelerServiceClient.obtenerViaje(dto.getViajeId(), token);

        Itinerario itinerario = Itinerario.builder()
                .viajeId(viaje.getId())
                .usuarioId(usuario.getUsuarioId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        Itinerario guardado = itinerarioRepository.save(itinerario);
        log.info("Itinerario creado exitosamente con id {}", guardado.getId());
        return toResponseDTO(guardado);
    }

    @Transactional
    public ItinerarioResponseDTO agregarLugar(Long itinerarioId, LugarItinerarioRequestDTO dto) {
        Itinerario itinerario = itinerarioRepository.findById(itinerarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerario no encontrado con id: " + itinerarioId));

        TipoLugar tipoLugar = tipoLugarRepository.findById(dto.getTipoLugarId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de lugar no encontrado con id: " + dto.getTipoLugarId()));

        LugarItinerario lugar = LugarItinerario.builder()
                .itinerario(itinerario)
                .tipoLugar(tipoLugar)
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .ordenVisita(dto.getOrdenVisita())
                .build();

        lugarItinerarioRepository.save(lugar);
        log.info("Lugar '{}' agregado al itinerario {}", dto.getNombre(), itinerarioId);

        return obtenerPorId(itinerarioId);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!itinerarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Itinerario no encontrado con id: " + id);
        }
        itinerarioRepository.deleteById(id);
        log.info("Itinerario {} eliminado", id);
    }

    @Transactional
    public void eliminarLugar(Long itinerarioId, Long lugarId) {
        LugarItinerario lugar = lugarItinerarioRepository.findById(lugarId)
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado con id: " + lugarId));

        if (!lugar.getItinerario().getId().equals(itinerarioId)) {
            throw new ResourceNotFoundException("El lugar no pertenece a este itinerario");
        }

        lugarItinerarioRepository.deleteById(lugarId);
        log.info("Lugar {} eliminado del itinerario {}", lugarId, itinerarioId);
    }

    private ItinerarioResponseDTO toResponseDTO(Itinerario i) {
        List<LugarItinerarioResponseDTO> lugares = lugarItinerarioRepository
                .findByItinerarioIdOrderByOrdenVisitaAsc(i.getId()).stream()
                .map(l -> LugarItinerarioResponseDTO.builder()
                        .id(l.getId())
                        .nombre(l.getNombre())
                        .direccion(l.getDireccion())
                        .ordenVisita(l.getOrdenVisita())
                        .tipoLugar(l.getTipoLugar().getNombre())
                        .build())
                .toList();

        return ItinerarioResponseDTO.builder()
                .id(i.getId())
                .viajeId(i.getViajeId())
                .usuarioId(i.getUsuarioId())
                .nombre(i.getNombre())
                .descripcion(i.getDescripcion())
                .fechaCreacion(i.getFechaCreacion())
                .lugares(lugares)
                .build();
    }

}
