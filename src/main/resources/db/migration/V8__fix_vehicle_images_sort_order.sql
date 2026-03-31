-- Corrige SMALLINT -> INTEGER para compatibilidade com Hibernate @OrderColumn
-- Hibernate valida sort_order como INTEGER (4 bytes); SMALLINT (2 bytes) falha no ddl-auto: validate
ALTER TABLE vehicle_images ALTER COLUMN sort_order TYPE INTEGER;
