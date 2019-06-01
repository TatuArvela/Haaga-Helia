CREATE TABLE henkilo (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	etunimi VARCHAR(40),
	sukunimi VARCHAR(40),
	puhelin VARCHAR(20),
	email VARCHAR(40),
	osoite VARCHAR(40),
	postinumero CHAR(5),
	postitoimipaikka VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
