-- Insert data into User table
INSERT INTO "tuser" (id, username, password) 
    VALUES (1, 'test1', '$2a$10$QKG2zDUMMG7g9316xA.oLuqYEYFRgO9bmHVVWj13rbltv5AFD1nWq')
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tuser" (id, username, password)
    VALUES (2, 'test2', '$2a$10$YmvI59i8bVjYzrZdZKHFbe4BObKAthip7vBvOcLVNBNYcxwNs1AFC')
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tuser" (id, username, password)
    VALUES (3, 'Vkehl', '$2a$10$oTxtncc92gC4fnCq9PbHS.y5zUiq/Ab67scGiFIK4JG.O.Dhp7JlG')
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Post table
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (1, 24, 25345, '2024-01-08', 'Das ist ein Post', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (2, 32265, 2324, '2024-01-08', 'Und das ist der zweite Post', 2)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (3, 24, 25345, '2024-01-08', 'Und das ist der dritte Post', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (4, 32265, 2324, '2024-01-08', 'Und das ist der letzte Post', 2)
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Comment table
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (1, 2345, 245, '2024-01-08', 'Das muss kommentiert werden', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (2, 2545, 87, '2024-01-08', 'Super Kommentar', 2, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (3, 2345, 245, '2024-01-08', 'Aber sowas von', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (4, 2545, 87, '2024-01-08', 'Letzter Kommentar', 2, 2)
    ON CONFLICT (id) DO NOTHING;
