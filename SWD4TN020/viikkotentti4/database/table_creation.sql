CREATE TABLE albumi (
	id INT NOT NULL AUTO_INCREMENT,
	nimi VARCHAR(255) NOT NULL,
	artisti VARCHAR(255) NOT NULL,
	julkaisuvuosi INT,
	genre VARCHAR(255),
	formaatti VARCHAR(30),
	PRIMARY KEY (id)
);