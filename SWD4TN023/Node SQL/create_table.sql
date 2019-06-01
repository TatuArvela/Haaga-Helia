CREATE TABLE persons (
  Id int NOT NULL AUTO_INCREMENT,
  LastName varchar(255),
  FirstName varchar(255),
  PRIMARY KEY (ID)
);

INSERT INTO persons (FirstName, LastName)
VALUES ('Pete', 'Patterson');