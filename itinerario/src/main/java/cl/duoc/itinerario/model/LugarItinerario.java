package cl.duoc.itinerario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugares_itinerario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LugarItinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerario_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Itinerario itinerario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_lugar_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TipoLugar tipoLugar;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(name = "orden_visita", nullable = false)
    @Builder.Default
    private Integer ordenVisita = 1;

}
