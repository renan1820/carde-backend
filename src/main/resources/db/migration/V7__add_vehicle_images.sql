CREATE TABLE vehicle_images (
    id          BIGSERIAL     PRIMARY KEY,
    vehicle_id  VARCHAR(36)   NOT NULL REFERENCES vehicles(id) ON DELETE CASCADE,
    url         VARCHAR(1000) NOT NULL,
    sort_order  SMALLINT      NOT NULL DEFAULT 0
);

CREATE INDEX idx_vehicle_images_vehicle_id ON vehicle_images (vehicle_id);

-- Migrar imagens existentes (image_url) para a nova tabela
INSERT INTO vehicle_images (vehicle_id, url, sort_order)
SELECT id, image_url, 0
FROM vehicles
WHERE image_url IS NOT NULL AND image_url != '';

ALTER TABLE vehicles DROP COLUMN image_url;
