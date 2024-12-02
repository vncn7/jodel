INSERT INTO USER (id, username, password) 
       VALUES ("1", "test1", "password");
INSERT INTO USER (id, username, password)
       VALUES ("2", "test2", "password");
INSERT INTO USER (id, username, password)
       VALUES ("3", "Vkehl", "password");
INSERT INTO Post (id, latitude, longitude, createdAt, text, authorId)
       VALUES ("1", "24", "25345", "2022.01.08", "Das ist ein Post", "1");
INSERT INTO Post (id, latitude, longitude, createdAt, text, authorId)
       VALUES ("2", "32265", "2324", "2022.01.08", "Und das ist der zweite Post", "2");
INSERT INTO Post (id, latitude, longitude, createdAt, text, authorId)
       VALUES ("3", "24", "25345", "2022.01.08", "Und das ist der dritte Post", "1");
INSERT INTO Post (id, latitude, longitude, createdAt, text, authorId)
       VALUES ("4", "32265", "2324", "2022.01.08", "Und das ist der letzte Post", "2");

INSERT INTO Comment (id, latitude, longitude, createdAt, text, authorId, postId)
       VALUES ("1", "2345", "245", "2022.01.08", "Das muss kommentiert werden", "1", "1");
INSERT INTO Comment (id, latitude, longitude, createdAt, text, authorId, postId)
       VALUES ("2", "2545", "87", "2022.01.08", "Super Kommentar", "2", "1");
INSERT INTO Comment (id, latitude, longitude, createdAt, text, authorId, postId)
       VALUES ("3", "2345", "245", "2022.01.08", "Aber sowas von", "1", "1");
INSERT INTO Comment (id, latitude, longitude, createdAt, text, authorId, postId)
       VALUES ("4", "2545", "87", "2022.01.08", "Letzter Kommentar", "2", "2");