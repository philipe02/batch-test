-- Create table 'person' if it does not exist
CREATE TABLE IF NOT EXISTS db.person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(11) NOT NULL
);

-- Create table 'address' if it does not exist
CREATE TABLE IF NOT EXISTS db.address (
    id SERIAL PRIMARY KEY,
    person_id INT NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES db.person(id)
);

-- Create table 'user' if it does not exist
CREATE TABLE IF NOT EXISTS db."user" (
    id SERIAL PRIMARY KEY,
    person_id INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES db.person(id)
);

-- Create table 'users_to_create' if it does not exist
CREATE TABLE IF NOT EXISTS db.users_to_create (
    id SERIAL PRIMARY KEY,
    person_id INT NOT NULL,
    created BOOLEAN NOT NULL DEFAULT FALSE,
    added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP,
    FOREIGN KEY (person_id) REFERENCES db.person(id)
);

-- Create table 'notification' if it does not exist
CREATE TABLE IF NOT EXISTS db.notification (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    message VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES db."user"(id)
);

-- Create table 'notifications_to_send' if it does not exist
CREATE TABLE IF NOT EXISTS db.notifications_to_send (
    id SERIAL PRIMARY KEY,
    notification_id INT NOT NULL,
    sent BOOLEAN NOT NULL DEFAULT FALSE,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sent_at TIMESTAMP,
    FOREIGN KEY (notification_id) REFERENCES db.notification(id)
);

-- Create table 'job_config' if it does not exist
CREATE TABLE IF NOT EXISTS db.job_config (
    id SERIAL PRIMARY KEY,
    job_name VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Insert initial data into 'person' table
INSERT INTO db.person (name, birthdate, cpf, email, phone)
VALUES ('John Doe', '1990-01-01', '12345678901', 'john@doe.com', '12345678901'),
       ('Jane Smith', '1985-05-15', '98765432101', 'jane@smith.com', '98765432101'),
       ('Michael Johnson', '1979-07-23', '45678912301', 'michael@johnson.com', '45678912301'),
       ('Emily Davis', '1992-11-30', '32165498701', 'emily@davis.com', '32165498701'),
       ('Robert Brown', '1968-03-12', '65412378901', 'robert@brown.com', '65412378901'),
       ('Linda Wilson', '2000-08-20', '78932165401', 'linda@wilson.com', '78932165401');