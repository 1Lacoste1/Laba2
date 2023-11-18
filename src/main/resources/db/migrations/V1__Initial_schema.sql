CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       birth_date DATE NOT NULL,
                       library_card_number VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE books (
                       isbn VARCHAR(20) PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author_list TEXT NOT NULL,
                       publication_year INT NOT NULL,
                       page_count INT NOT NULL
);

CREATE TABLE issues (
                        issue_id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        isbn VARCHAR(20) NOT NULL,
                        issue_date DATE NOT NULL,
                        usage_period INT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(user_id),
                        FOREIGN KEY (isbn) REFERENCES books(isbn)
);
