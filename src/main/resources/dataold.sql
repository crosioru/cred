DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS cardholder;
DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
     payment_id INT AUTO_INCREMENT PRIMARY KEY,
     pan CHAR(16) NOT NULL,
     expiry CHAR(4) NOT NULL,
     cvv CHAR(3) NOT NULL
);

CREATE TABLE card (
      card_id INT AUTO_INCREMENT PRIMARY KEY,
      pan CHAR(16) NOT NULL,
      expiry CHAR(4) NOT NULL,
      cvv CHAR(3) NOT NULL
);

CREATE TABLE cardholder (
      cardholder_id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(250) NOT NULL,
      email VARCHAR(250) NOT NULL
);

-- INSERT INTO card (pan, expiry, cvv, payment_id) VALUES
--     ('4200000000000001', '0624', '789', 1),
--     ('4200000000000002', '0625', '123', 2,
--     ('4200000000000003', '0626', '456', 3);
--
-- -- INSERT INTO cardholder (name, email) VALUES
-- --     ('FirstName1 LastName1', 'email1@aaa.com'),
-- --     ('FirstName2 LastName2', 'email2@bbb.com'),
-- --     ('FirstName3 LastName3', 'email3@ccc.com');
-- --
-- INSERT INTO payment (amount, currency) VALUES
-- (20.21, 'EUR'),
-- (144.00, 'CAD'),
-- (152478.00, 'NZD');