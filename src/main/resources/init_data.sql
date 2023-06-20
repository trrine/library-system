-- create tables

CREATE TABLE IF NOT EXISTS User (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    type VARCHAR(9) NOT NULL,
    CONSTRAINT CHK_User CHECK (UPPER(type) IN ('BORROWER', 'LIBRARIAN'))
);

CREATE TABLE IF NOT EXISTS Book (
    bookNo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(80) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    status VARCHAR(9) NOT NULL,
    CONSTRAINT CHK_Book CHECK (UPPER(status) IN ('AVAILABLE', 'BORROWED'))
);

CREATE TABLE IF NOT EXISTS Loan (
    userID INT UNSIGNED NOT NULL,
    bookNo INT UNSIGNED NOT NULL,
    startDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    status VARCHAR(8) NOT NULL,
    PRIMARY KEY (userID, bookNo, startDate),
    FOREIGN KEY (userID) REFERENCES User(id),
    FOREIGN KEY (bookNo) REFERENCES Book (bookNo),
    CONSTRAINT CHK_Loan CHECK (UPPER(status) in ('ACTIVE', 'RETURNED'))
);

-- load tables

INSERT INTO User (firstname, surname, phone, email, type) VALUES
('John', 'Doe', '123456789', 'johndoe@example.com', 'BORROWER'),
('Jane', 'Smith', '987654321', 'janesmith@example.com', 'LIBRARIAN');

INSERT INTO Book (isbn, title, author, publisher, status) VALUES
('9781234567890', 'Book 1', 'Author 1', 'Publisher A', 'AVAILABLE'),
('9780987654321', 'Book 2', 'Author 2', 'Publisher B', 'BORROWED');

INSERT INTO Loan (userID, bookNo, startDate, dueDate, status) VALUES
(1, 2, '2023-06-01', '2023-06-15', 'ACTIVE'),
(2, 1, '2023-06-05', '2023-06-19', 'RETURNED');
