CREATE TABLE IF NOT EXISTS lugares_itinerario (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    itinerario_id   BIGINT NOT NULL,
    tipo_lugar_id   BIGINT NOT NULL,
    nombre          VARCHAR(150) NOT NULL,
    direccion       VARCHAR(255),
    orden_visita    INT NOT NULL DEFAULT 1,
    CONSTRAINT fk_lugar_itinerario FOREIGN KEY (itinerario_id) REFERENCES itinerarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_lugar_tipo FOREIGN KEY (tipo_lugar_id) REFERENCES tipos_lugar(id)
);