-- Corrige divergência entre SMALLINT (banco) e int (entidades JPA)
-- Hibernate mapeia Java int -> INTEGER (4 bytes); SMALLINT (2 bytes) falha na validação de schema
ALTER TABLE vehicles ALTER COLUMN year TYPE INTEGER;
ALTER TABLE vehicles ALTER COLUMN display_order TYPE INTEGER;
ALTER TABLE vehicle_specs ALTER COLUMN sort_order TYPE INTEGER;
