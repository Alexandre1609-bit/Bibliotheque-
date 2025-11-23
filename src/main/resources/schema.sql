USE library;

DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;


CREATE TABLE books (
    books_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    stock INT,
    PRIMARY KEY (books_id) 
);

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	email VARCHAR(100),
	password CHAR(64), -- CHAR(64) car j'utiliserais un encryptage SHA-256, CHAR est plus rigoureux, il est fait pour une longeur fixe
	PRIMARY KEY (user_id)
);

CREATE TABLE loans (
	loan_id INT NOT NULL AUTO_INCREMENT,
    borrow_date DATE,
    return_date DATE, 
    return_date_real DATE, -- reste NULL tant que le livre n'est pas retourné
	
    books_id_fk INT,
    user_id_fk INT,
    PRIMARY KEY (loan_id),
    
    FOREIGN KEY (books_id_fk) REFERENCES books(books_id),
    FOREIGN KEY (user_id_fk) REFERENCES users(user_id)
);

INSERT INTO books (title, author, stock)
VALUES 
	('Dune', 'Frank Herbert', 5),
    ('La Passe-miroir Tome 1', 'Christelle Dabos', 3),
    ('La Passe-miroir Tome 2', 'Christelle Dabos', 6),
    ('1984', 'George Orwell', 7);
    
INSERT INTO users (name, email, password)
VALUES
	('Alexandre', 'test01@gmail.com', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f'),
    ('Antoine', 'test62@gmail.com', '957d04457ccc946390b5a96bfa15dd75500d726babe948efe44d398fd55e0e04'),
    ('Quentin', 'plante02@gmail.com', '32cc599d0b40f7e08ef67cd857f95572a5fd703a4aaf8d7a08a6cbdeb509480c');
    
INSERT INTO loans (borrow_date, return_date, return_date_real, books_id_fk, user_id_fk)
VALUES 
	('2025-11-17', '2025-11-23', NULL, 1, 1),
    ('2025-11-10', '2025-11-17', '2025-11-17', 2, 3);