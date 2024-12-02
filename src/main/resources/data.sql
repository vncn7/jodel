-- Create the User table
CREATE TABLE IF NOT EXISTS "User" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create the Post table
CREATE TABLE IF NOT EXISTS "Post" (
    id SERIAL PRIMARY KEY,
    latitude INT,
    longitude INT,
    createdAt DATE NOT NULL,
    text TEXT,
    authorId INT NOT NULL,
    FOREIGN KEY (authorId) REFERENCES "User"(id)
);

-- Create the Comment table
CREATE TABLE IF NOT EXISTS "Comment" (
    id SERIAL PRIMARY KEY,
    latitude INT,
    longitude INT,
    createdAt DATE NOT NULL,
    text TEXT,
    authorId INT NOT NULL,
    postId INT NOT NULL,
    FOREIGN KEY (authorId) REFERENCES "User"(id),
    FOREIGN KEY (postId) REFERENCES "Post"(id)
);
-- Insert data into User table
INSERT INTO "User" (id, username, password) 
    VALUES (1, 'test1', 'password')
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "User" (id, username, password)
    VALUES (2, 'test2', 'password')
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "User" (id, username, password)
    VALUES (3, 'Vkehl', 'password')
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Post table
INSERT INTO "Post" (id, latitude, longitude, createdAt, text, authorId)
    VALUES (1, 24, 25345, '2024-01-08', 'Das ist ein Post', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Post" (id, latitude, longitude, createdAt, text, authorId)
    VALUES (2, 32265, 2324, '2024-01-08', 'Und das ist der zweite Post', 2)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Post" (id, latitude, longitude, createdAt, text, authorId)
    VALUES (3, 24, 25345, '2024-01-08', 'Und das ist der dritte Post', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Post" (id, latitude, longitude, createdAt, text, authorId)
    VALUES (4, 32265, 2324, '2024-01-08', 'Und das ist der letzte Post', 2)
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Comment table
INSERT INTO "Comment" (id, latitude, longitude, createdAt, text, authorId, postId)
    VALUES (1, 2345, 245, '2024-01-08', 'Das muss kommentiert werden', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Comment" (id, latitude, longitude, createdAt, text, authorId, postId)
    VALUES (2, 2545, 87, '2024-01-08', 'Super Kommentar', 2, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Comment" (id, latitude, longitude, createdAt, text, authorId, postId)
    VALUES (3, 2345, 245, '2024-01-08', 'Aber sowas von', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "Comment" (id, latitude, longitude, createdAt, text, authorId, postId)
    VALUES (4, 2545, 87, '2024-01-08', 'Letzter Kommentar', 2, 2)
    ON CONFLICT (id) DO NOTHING;
