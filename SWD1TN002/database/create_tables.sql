CREATE TABLE posti (
postinro CHAR(5) NOT NULL PRIMARY KEY,
postitmp VARCHAR(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE asiakas (
id INT NOT NULL PRIMARY KEY,
etunimi VARCHAR(20) NOT NULL,
sukunimi VARCHAR(30) NOT NULL,
osoite VARCHAR(40) NOT NULL,
puhelin VARCHAR(20) NOT NULL,
postinro CHAR(5) NOT NULL,
FOREIGN KEY (postinro) REFERENCES posti(postinro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auto(
rekno VARCHAR(7) NOT NULL PRIMARY KEY,
malli VARCHAR(20) NOT NULL,
merkki VARCHAR(20) NOT NULL,
vrkhinta DECIMAL(5,2) NOT NULL,
huoltopvm DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE vuokraus (
numero INT NOT NULL AUTO_INCREMENT,
vuokrauspvm DATE NOT NULL,
paattymispvm DATE NOT NULL,
kokonaishinta  DECIMAL(8,2) NOT NULL,
maksupvm DATE,
vuokraaja INT NOT NULL,
vuokrakohde VARCHAR(7) NOT NULL,
PRIMARY KEY (numero),
FOREIGN KEY (vuokraaja) REFERENCES asiakas(id),
FOREIGN KEY (vuokrakohde) REFERENCES auto(rekno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;