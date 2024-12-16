CREATE TABLE customer (
  customer_id int NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  age int DEFAULT NULL,
  gender varchar(255) DEFAULT NULL,
  identification varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  status bit(1) DEFAULT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE account (
  account_number varchar(255) NOT NULL,
  account_type varchar(255) DEFAULT NULL,
  balance double DEFAULT NULL,
  status bit(1) DEFAULT NULL,
  customer_id int DEFAULT NULL,
  initial_balance double DEFAULT NULL,
  PRIMARY KEY (account_number),
  KEY ACCFK01 (customer_id),
  CONSTRAINT ACC01 FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE movement (
  movement_id int NOT NULL AUTO_INCREMENT,
  amount double DEFAULT NULL,
  balance double DEFAULT NULL,
  movement_date date DEFAULT NULL,
  movement_type enum('CREDIT','DEBIT') DEFAULT NULL,
  account_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (movement_id),
  KEY MOVFK01 (account_number),
  CONSTRAINT MOVCC01 FOREIGN KEY (account_number) REFERENCES account (account_number)
);
