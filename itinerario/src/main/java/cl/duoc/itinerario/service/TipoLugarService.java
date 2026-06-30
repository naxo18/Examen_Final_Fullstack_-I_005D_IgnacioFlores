package cl.duoc.itinerario.service;

import cl.duoc.itinerario.dto.TipoLugarResponseDTO;
import cl.duoc.itinerario.repository.TipoLugarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TipoLugarService {

     private final TipoLugarRepository tipoLugarRepository;

    @Transactional(readOnly = true)
    public List<TipoLugarResponseDTO> listarTodos() {
        return tipoLugarRepository.findAll().stream()
                .map(t -> TipoLugarResponseDTO.builder().id(t.getId()).nombre(t.getNombre()).build())
                .toList();
    }

}
