ALTER TABLE museum_events
    ADD COLUMN display_order INTEGER NOT NULL DEFAULT 0;

-- Preserva a ordem atual por data para os eventos existentes
UPDATE museum_events SET display_order = sub.rn - 1
FROM (
    SELECT id, ROW_NUMBER() OVER (ORDER BY event_date ASC) AS rn
    FROM museum_events
) sub
WHERE museum_events.id = sub.id;
