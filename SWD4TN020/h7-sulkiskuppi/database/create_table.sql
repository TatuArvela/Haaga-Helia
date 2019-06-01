CREATE TABLE pelaaja (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	etunimi varchar(100) NOT NULL,
	sukunimi varchar(100) NOT NULL,
	UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ottelu (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pvm DATETIME NOT NULL,
	joukkue1_pelaaja1 int NOT NULL,
	joukkue1_pelaaja2 int,
	joukkue2_pelaaja1 int NOT NULL,
	joukkue2_pelaaja2 int,
	joukkue1_pisteet int NOT NULL,
	joukkue2_pisteet int NOT NULL,
	FOREIGN KEY (joukkue1_pelaaja1) REFERENCES pelaaja(id),
	FOREIGN KEY (joukkue1_pelaaja2) REFERENCES pelaaja(id),
	FOREIGN KEY (joukkue2_pelaaja1) REFERENCES pelaaja(id),
	FOREIGN KEY (joukkue2_pelaaja2) REFERENCES pelaaja(id),
	UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;