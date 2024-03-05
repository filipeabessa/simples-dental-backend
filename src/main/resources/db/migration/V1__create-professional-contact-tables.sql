CREATE TABLE professionals(
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              position VARCHAR(255) NOT NULL,
                              birth_date DATE NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contacts(
                         id SERIAL PRIMARY KEY,
                         professional_id BIGINT NOT NULL,
                         contact VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (professional_id) REFERENCES professionals(id)
);