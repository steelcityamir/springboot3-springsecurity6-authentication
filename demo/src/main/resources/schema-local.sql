DROP TABLE IF EXISTS account;
CREATE SEQUENCE ACCOUNT_SEQ START WITH 1 INCREMENT BY 1;
CREATE TABLE account (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          created_on TIMESTAMP NOT NULL,
                          email VARCHAR (255) UNIQUE NOT NULL,
                          password VARCHAR (255) NOT NULL,
                          enabled BOOLEAN NOT NULL
);


drop table if exists login;
CREATE SEQUENCE LOGIN_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE login (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       created_on TIMESTAMP NOT NULL,
                       ip VARCHAR (255) NOT NULL,
                       email VARCHAR (255) NOT NULL,
                       success BOOLEAN NOT NULL,
                       reason VARCHAR(255)
);

