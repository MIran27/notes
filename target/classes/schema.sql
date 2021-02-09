CREATE TABLE IF NOT EXISTS NOTE_USER (
    username VARCHAR(512) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS NOTE (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    title VARCHAR(512) NOt NULL,
    note TEXT NOT NULL ,
    archive BOOLEAN Default (false),
    user_username VARCHAR(128),
    PRIMARY KEY (id),
    FOREIGN KEY (user_username) REFERENCES note_user(username) ON DELETE CASCADE
);
