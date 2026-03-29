CREATE TABLE vehicle_specs (
    id         BIGSERIAL    PRIMARY KEY,
    vehicle_id VARCHAR(36)  NOT NULL REFERENCES vehicles(id) ON DELETE CASCADE,
    spec_key   VARCHAR(100) NOT NULL,
    spec_value VARCHAR(500) NOT NULL,
    sort_order SMALLINT     NOT NULL DEFAULT 0,
    CONSTRAINT uq_vehicle_spec_key UNIQUE (vehicle_id, spec_key)
);

CREATE INDEX idx_vehicle_specs_vehicle_id ON vehicle_specs (vehicle_id);
