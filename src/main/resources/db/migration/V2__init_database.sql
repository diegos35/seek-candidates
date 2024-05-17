CREATE TABLE IF NOT EXISTS user (
                      username VARCHAR(20) NOT NULL PRIMARY KEY,
                      password VARCHAR(200) NOT NULL,
                      email VARCHAR(50),
                      locked TINYINT NOT NULL,
                      disabled TINYINT NOT NULL
);

INSERT INTO user (username, password, email, locked, disabled)
VALUES
    ('admin', '$2y$10$dEQvCzZDefxXZu8mrkPdC.79O0iPY9pQWUF5hu7eJLBnTFhfoeJVm', 'admin@gmail.com', 0, 0),
    ('diego', '$2y$10$WNB26/IoNAYrUJwXLzRCTO5.SPiCF.s.oWbPO.b9NXm3ZADDz0mJe', 'diego@gmail.com', 0, 0),
                                                                   ('user2', 'password2', 'user2@example.com', 0, 0);
