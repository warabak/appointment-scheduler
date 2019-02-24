-- All *.sql files living under db/migration will be automatically applied by Flyway.
-- This V1 file establishes the initial schema of the database.

-- Allow for the addition of future statuses
CREATE TABLE statuses (
  id SERIAL PRIMARY KEY,
  status TEXT NOT NULL
);

CREATE TABLE appointments (

  -- The PK of the appointments table
  id SERIAL PRIMARY KEY,

  -- The creation timestamp of the row
  created_at TIMESTAMP NOT NULL,

  -- The timestamp of the appointment, which will be stored in UTC
  appointment_date TIMESTAMP NOT NULL,

  -- The duration of the appointment, in minutes
  appointment_duration INTEGER NOT NULL,

  -- We will store the name of the doctor as one big string here, but this would be a prime candidate for normalization (think : doctor_id FK)
  name_of_doctor TEXT NOT NULL,

  -- Foreign key to the Statuses table
  status_id INTEGER REFERENCES statuses(id),

  -- We will opt to only store in USD, forcing a precision down to the penny.
  -- If we want to accept alternative currencies (even crypto currencies, where the precision might be far beyond two decimal places), prefer the use of outer
  -- services to convert to a canonical representation (USD) before storing in the database.
  -- Opt to store, for now, up to one billion dollars down the penny.
  price NUMERIC(12, 2) NOT NULL

);

-- Create our two initial statuses
INSERT INTO statuses (status)
VALUES
  ('Available'),
  ('Booked');