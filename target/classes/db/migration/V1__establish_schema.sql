-- All *.sql files living under db/migration will be automatically applied by Flyway.
-- This V1 file establishes the initial schema of the database.

-- Allow for the addition of future statuses
CREATE TABLE statuses (
  id SERIAL PRIMARY KEY,
  status TEXT NOT NULL
);

CREATE TABLE appointments (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMP NOT NULL,
  appointment_date TIMESTAMP NOT NULL,
  appointment_duration INTEGER,
  name_of_doctor TEXT,
  status_id INTEGER REFERENCES statuses(id),
  price NUMERIC(2) NOT NULL
);

-- Create our two initial statuses
INSERT INTO statuses (status)
VALUES
  ('Available'),
  ('Booked');