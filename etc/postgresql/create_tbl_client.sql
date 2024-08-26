CREATE TABLE client (
    ID SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    name VARCHAR(255) NOT NULL,
    street_name VARCHAR(255),
    complement VARCHAR(255),
    number VARCHAR(10),
    neighborhood VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(2)
);