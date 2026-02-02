-- Flyway callback: runs before each migrate
-- Ensures schema "erp" exists (erp_base_db.erp.table_name)
CREATE SCHEMA IF NOT EXISTS erp;
