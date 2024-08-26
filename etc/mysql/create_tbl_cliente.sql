CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(14),
    nome VARCHAR(255),
    rua VARCHAR(255),
    complemento VARCHAR(255),
    numero VARCHAR(10),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    email VARCHAR(255),
    telefone VARCHAR(20)
);
