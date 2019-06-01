CREATE TABLE kayttaja_sanat (
	id int NOT NULL auto_increment PRIMARY KEY,
	username varchar(255) NOT NULL UNIQUE,
	password_encrypted varchar(255) NOT NULL,
	enabled tinyint NOT NULL,
	firstname varchar(255) default NULL,
	lastname varchar(255) default NULL,
	role varchar(255) NOT NULL DEFAULT "ROLE_USER"
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE sana (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
	sana varchar(100) NOT NULL,
	seloste text NOT NULL,
	kieli varchar(50) NOT NULL,
	lisaaja varchar(255) NOT NULL,
	FOREIGN KEY (lisaaja) REFERENCES kayttaja_sanat(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
