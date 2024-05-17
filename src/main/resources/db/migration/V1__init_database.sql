CREATE TABLE IF NOT EXISTS candidates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    gender VARCHAR(10),
    salary_expected DECIMAL(10, 2)
);

INSERT INTO candidates (name, email, gender, salary_expected)
VALUES
    ('John Doe', 'john@example.com', 'Male', 50000.00),
    ('Jane Smith', 'jane@gmail.com', 'Female', 60000.00),
    ('Diego Caviedes', 'diego@gmail.com', 'Female', 30000.00),
    ('James Rodriguez', 'jamer@gmail.com', 'Female', 1000.00),
    ('Ivone Tatiana', 'ivone@gmail.com', 'Female', 5000.00)
;
