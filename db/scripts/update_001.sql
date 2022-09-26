CREATE TABLE IF NOT EXISTS tasks (
                       id SERIAL PRIMARY KEY,
                       name TEXT,
                       description TEXT,
                       created TIMESTAMP,
                       done BOOLEAN
);

CREATE TABLE IF NOT EXISTS todo_user (
                                      id SERIAL PRIMARY KEY,
                                      name TEXT,
                                      login VARCHAR(100),
                                      password VARCHAR(100)
);