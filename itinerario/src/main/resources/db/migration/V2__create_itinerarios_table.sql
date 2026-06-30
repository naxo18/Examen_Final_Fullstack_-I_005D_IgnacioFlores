CREATE TABLE IF NOT EXISTS itinerarios (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    viaje_id        BIGINT NOT NULL,
    usuario_id      BIGINT NOT NULL,
    nombre          VARCHAR(150) NOT NULL,
    descripcion     VARCHAR(255),
    fecha_creacion  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);