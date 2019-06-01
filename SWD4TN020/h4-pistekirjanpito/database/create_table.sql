CREATE TABLE kayttaja (
	tunnus varchar(30) NOT NULL PRIMARY KEY,
	salasana varchar(100) NOT NULL,
	etunimi varchar(100) NOT NULL,
	sukunimi varchar(100) NOT NULL,
	onopettaja boolean NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pistetieto (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	opiskelijatunnus varchar(30) NOT NULL,
	kurssi varchar(30) NOT NULL,
	tehtava varchar(100) NOT NULL,
	pisteet int NOT NULL DEFAULT 0,
	FOREIGN KEY (opiskelijatunnus) REFERENCES kayttaja(tunnus)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;