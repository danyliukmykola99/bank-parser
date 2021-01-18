CREATE DATABASE IF NOT EXISTS ukrsib;

USE ukrsib;

CREATE TABLE IF NOT EXISTS transactions (
                                     transaction_id int(100) NOT NULL AUTO_INCREMENT,
                                     place varchar(50) DEFAULT NULL,
                                     amount double DEFAULT NULL,
                                     currency varchar(50) DEFAULT NULL,
                                     card varchar(50) DEFAULT NULL,
                                     firstName varchar(50) DEFAULT NULL,
                                     lastName varchar(50) DEFAULT NULL,
                                     middleName varchar(50) DEFAULT NULL,
                                     inn int DEFAULT NULL,
                                     PRIMARY KEY (transaction_id)
);
