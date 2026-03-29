CREATE TABLE museum_events (
    id          VARCHAR(36)   PRIMARY KEY,
    title       VARCHAR(300)  NOT NULL,
    description TEXT          NOT NULL,
    event_date  TIMESTAMPTZ   NOT NULL,
    image_url   VARCHAR(1000) NOT NULL,
    is_featured BOOLEAN       NOT NULL DEFAULT FALSE,
    is_active   BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_events_date     ON museum_events (event_date DESC);
CREATE INDEX idx_events_featured ON museum_events (is_featured, event_date DESC)
    WHERE is_active = TRUE;
