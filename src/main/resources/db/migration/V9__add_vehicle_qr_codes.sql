CREATE TABLE vehicle_qr_codes (
    id           VARCHAR(36)    PRIMARY KEY,
    vehicle_id   VARCHAR(36)    NOT NULL UNIQUE
                                REFERENCES vehicles(id) ON DELETE CASCADE,
    qr_value     VARCHAR(2000)  NOT NULL UNIQUE,
    image_url    VARCHAR(2000)  NOT NULL,
    created_at   TIMESTAMPTZ    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_vehicle_qr_codes_vehicle_id ON vehicle_qr_codes (vehicle_id);
