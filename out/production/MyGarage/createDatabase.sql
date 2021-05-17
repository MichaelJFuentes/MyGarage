-- "C:\\Users\\micha\\IdeaProjects\\MyGarage\\src\\createDatabase.sql"
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS garages;
DROP TABLE IF EXISTS employeeTypes;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS makes;

-- todo ON UPDATE CASCADE ON DELETE CASCADE onto foreign keys

CREATE TABLE makes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE
);
CREATE TABLE models (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL,
    make_id INTEGER NOT NULL,
    FOREIGN KEY(make_id) REFERENCES makes(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE customers(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT NOT NULL,
    zip TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TABLE cars(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    vin TEXT NOT NULL UNIQUE,
    year INTEGER NOT NULL,
    miles INTEGER NOT NULL DEFAULT 0,
    model_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    FOREIGN KEY(model_id) REFERENCES models(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(customer_id) REFERENCES customers(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE employeeTypes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE
);
CREATE TABLE garages(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    location TEXT NOT NULL UNIQUE
);
CREATE TABLE employees(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT NOT NULL,
    garage_id INTEGER NOT NULL ,
    employeeType_id INTEGER NOT NULL,
    FOREIGN KEY(garage_id) REFERENCES garages(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(employeeType_id) REFERENCES employeeTypes(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE appointments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    car_id INTEGER NOT NULL ,
    garage_id INTEGER NOT NULL,
    FOREIGN KEY(car_id) REFERENCES cars(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(garage_id) REFERENCES garages(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE notes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    note TEXT NOT NULL,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    car_id INTEGER NOT NULL,
    mechanic_id INTEGER NOT NULL ,
    FOREIGN KEY(car_id) REFERENCES cars(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(mechanic_id) REFERENCES employees(id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO employeeTypes(name) VALUES ("manager"), ("mechanic")

-- -- INSERT INTO customers(username, password, email, phone, zip) VALUES("mike12", "123", "mike@gmail.com",
-- --     "281-324-2345", "76140");
--
-- -- INSERT INTO cars( vin, "year", miles, model_id, customer_id) VALUES ("VINONETEST", "1999", 250000, 4, 1);
--
-- SELECT
--     cars.id,
--     cars.vin,
--     cars.miles,
--     makes.name,
--     models.name,
--     customers.email
-- from cars
-- INNER JOIN models ON models.id = cars.model_id
-- INNER JOIN makes ON makes.id = models.make_id
-- INNER JOIN customers ON customers.id = cars.customer_id
-- WHERE cars.vin = "VINONETEST";
-- --
-- -- INSERT INTO garages(name, location) VALUES ("Gas Monkey", "2330 Merrell Rd, Dallas Tx");
-- --
-- -- INSERT INTO appointments(car_id, garage_id) VALUES (1, 1);
-- -- for searching based on the vin
-- SELECT
--     appointments.date_time,
--     cars.vin,
--     makes.name,
--     models.name,
--     cars.year,
--     garages.name
-- FROM appointments
-- INNER JOIN cars ON cars.id = appointments.car_id
-- INNER JOIN models ON cars.model_id = models.id
-- INNER JOIN makes ON makes.id = models.make_id
-- INNER JOIN garages on appointments.garage_id = appointments.garage_id
-- WHERE cars.vin = "VINONETEST"
-- ORDER BY appointments.date_time DESC;
--
-- -- for searching based on the user email
-- SELECT
--     appointments.date_time,
--     cars.vin,
--     makes.name,
--     models.name,
--     cars.year,
--     garages.name
-- FROM appointments
-- INNER JOIN cars ON cars.id = appointments.car_id
-- INNER JOIN models ON cars.model_id = models.id
-- INNER JOIN makes ON makes.id = models.make_id
-- INNER JOIN garages on appointments.garage_id = appointments.garage_id
-- INNER JOIN customers ON cars.customer_id = customers.id
-- WHERE customers.email = "mike@gmail.com"
-- ORDER BY appointments.date_time DESC;
--
-- INSERT INTO employees(username, password, email, phone, garage_id, employeeType_id) VALUES ("bob123", "password",
-- "bob@gmail.com", "234532647", 1, 2);
--
-- INSERT INTO notes(note, car_id, mechanic_id) VALUES("Testing note one, everything looks good in you car ",
--     1, 1);
--
--
-- SELECT
--     cars.id, cars.vin, cars.year, cars.miles, makes.name, models.name, customers.email
-- FROM cars
-- INNER JOIN models ON models.id = cars.model_id
-- INNER JOIN makes ON makes.id = models.make_id
-- INNER JOIN customers ON customers.id = cars.customer_id
-- WHERE customers.email = "mike@gmail.com";
--
-- SELECT cars.id, cars.vin, cars.year, cars.miles, makes.name, models.name, customers.email
-- FROM cars
-- INNER JOIN models ON models.id = cars.model_id
-- INNER JOIN makes ON makes.id = models.make_id
-- INNER JOIN customers ON customers.id = cars.customer_id
-- WHERE customers.email = "mike@gmail.com"
--
-- SELECT appointments.date_time, cars.vin, makes.name, models.name, cars.year, garages.name
-- FROM appointments INNER JOIN cars ON cars.id = appointments.car_id INNER JOIN models ON models.id = cars.model_id
-- INNER JOIN makes ON makes.id = models.make_id INNER JOIN garages ON garages.id = appointments.garage_id
-- ORDER BY appointments.date_time DESC;
--
--







