-- create tables

CREATE TABLE IF NOT EXISTS User (
    userID VARCHAR(50) PRIMARY KEY,
    password_hash BLOB NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    type VARCHAR(9) NOT NULL,
    CONSTRAINT CHK_User CHECK (UPPER(type) IN ('BORROWER', 'LIBRARIAN'))
);

CREATE TABLE IF NOT EXISTS Book (
    bookNo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(80) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    status VARCHAR(9) NOT NULL,
    CONSTRAINT CHK_Book CHECK (UPPER(status) IN ('AVAILABLE', 'BORROWED'))
);

CREATE TABLE IF NOT EXISTS Borrowing (
    userID INT UNSIGNED NOT NULL,
    bookNo INT UNSIGNED NOT NULL,
    startDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    status VARCHAR(8) NOT NULL,
    PRIMARY KEY (userID, bookNo, startDate),
    FOREIGN KEY (userID) REFERENCES User(id),
    FOREIGN KEY (bookNo) REFERENCES Book (bookNo),
    CONSTRAINT CHK_Borrowing CHECK (UPPER(status) in ('ACTIVE', 'RETURNED'))
);

-- load tables

INSERT INTO User (userID, password_hash, firstname, surname, phone, email, type) VALUES
('john', '$2a$10$S5fM3zKsUePQhxzkkNS4ROuyJYIw8dpNtNlACb1R3XWIFgPxl42JO', 'John', 'Doe', '1234567890', 'john@example.com', 'BORROWER'),
('alice', '$2a$10$S5fM3zKsUePQhxzkkNS4ROuyJYIw8dpNtNlACb1R3XWIFgPxl42JO', 'Alice', 'Smith', '9876543210', 'alice@example.com', 'LIBRARIAN');

INSERT INTO Book (isbn, title, author, publisher, status) VALUES
('9781234567890', 'Book 1', 'Author 1', 'Publisher A', 'AVAILABLE'),
('9780987654321', 'Book 2', 'Author 2', 'Publisher B', 'BORROWED');

INSERT INTO Borrowing (userID, bookNo, startDate, dueDate, status) VALUES
(1, 2, '2023-06-01', '2023-06-15', 'ACTIVE'),
(2, 1, '2023-06-05', '2023-06-19', 'RETURNED');
