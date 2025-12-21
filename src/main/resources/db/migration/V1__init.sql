CREATE TABLE publisher (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           country VARCHAR(255)
);


CREATE TABLE author (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INT
);

CREATE TABLE book (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      price DOUBLE PRECISION NOT NULL,
                      publisher_id INT,
                      CONSTRAINT fk_publisher
                          FOREIGN KEY (publisher_id) REFERENCES publisher(id) ON DELETE SET NULL
);


CREATE TABLE book_authors (
                              book_id INT NOT NULL,
                              author_id INT NOT NULL,

                              CONSTRAINT pk_book_authors PRIMARY KEY (book_id, author_id),

                              CONSTRAINT fk_book_authors_book
                                  FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,

                              CONSTRAINT fk_book_authors_author
                                  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
