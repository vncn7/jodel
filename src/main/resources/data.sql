-- Insert data into User table
INSERT INTO "tuser" (id, username, password) 
    VALUES (1, 'test1', '$2a$10$QKG2zDUMMG7g9316xA.oLuqYEYFRgO9bmHVVWj13rbltv5AFD1nWq') -- password: password
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tuser" (id, username, password)
    VALUES (2, 'test2', '$2a$10$YmvI59i8bVjYzrZdZKHFbe4BObKAthip7vBvOcLVNBNYcxwNs1AFC') -- password: password
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tuser" (id, username, password)
    VALUES (3, 'Vkehl', '$2a$10$oTxtncc92gC4fnCq9PbHS.y5zUiq/Ab67scGiFIK4JG.O.Dhp7JlG') -- password: password
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Post table
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (1, 48.737195, 9.295724, '2024-01-08', 'Das ist ein Post aus Esslingen am Neckar', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (2, 32265, 2324, '2024-01-08', 'Und das ist der zweite Post', 2)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (3, 48.740841, 9.329965, '2024-01-08', 'Und das hier ist ein anderer Post aus Esslingen', 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tpost" (id, latitude, longitude, created_At, text, author_Id)
    VALUES (4, 32265, 2324, '2024-01-08', 'Und das ist der letzte Post', 2)
    ON CONFLICT (id) DO NOTHING;

-- Insert data into Comment table
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (1, 2345, 245, '2024-01-08', 'Kommentar Nummer Eins!', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (2, 2545, 87, '2024-01-08', 'Kommentar Nummer Zwei!', 2, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (3, 2345, 245, '2024-01-08', 'Kommentar Nummer Drei!', 1, 1)
    ON CONFLICT (id) DO NOTHING;
INSERT INTO "tcomment" (id, latitude, longitude, created_At, text, author_Id, post_Id)
    VALUES (4, 2545, 87, '2024-01-08', 'Kommentar Nummer Vier!', 2, 2)
    ON CONFLICT (id) DO NOTHING;
