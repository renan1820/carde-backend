CREATE TABLE vehicles (
    id                VARCHAR(36)   PRIMARY KEY,
    name              VARCHAR(200)  NOT NULL,
    brand             VARCHAR(100)  NOT NULL,
    year              SMALLINT      NOT NULL CHECK (year BETWEEN 1800 AND 2100),
    category          VARCHAR(20)   NOT NULL
        CHECK (category IN ('car','motorcycle','truck','bus','racing','classic')),
    short_description VARCHAR(500)  NOT NULL,
    full_history      TEXT          NOT NULL,
    image_url         VARCHAR(1000) NOT NULL,
    engine_sound_url  VARCHAR(1000),
    display_order     SMALLINT      NOT NULL DEFAULT 0,
    is_active         BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at        TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_vehicles_category ON vehicles (category);
CREATE INDEX idx_vehicles_active   ON vehicles (is_active, display_order);
