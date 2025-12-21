
INSERT INTO publisher (name, country)
VALUES
    ('Penguin Books', 'UK'),
    ('HarperCollins', 'USA'),
    ('Astana Print', 'Kazakhstan');


INSERT INTO author (name, age)
VALUES
    ('George Orwell', 46),
    ('J.K. Rowling', 58),
    ('Chingiz Aitmatov', 79);


INSERT INTO book (title, price, publisher_id)
VALUES
    ('1984', 10.99, 1),
    ('Harry Potter', 20.49, 2),
    ('The Day Lasts More Than a Hundred Years', 15.00, 3);


INSERT INTO book_authors (book_id, author_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);
