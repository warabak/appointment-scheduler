-- All *.sql files living under db/migration will be automatically applied by Flyway.
-- This V1 file establishes the initial schema of the database.

CREATE TABLE appointments (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMP NOT NULL,
  appointment_date TIMESTAMP NOT NULL,
  appointment_duration INTEGER,
  name_of_doctor TEXT,
  status TEXT,
  price NUMERIC(2)
);