CREATE TABLE asukas (
	id int NOT NULL,
	nimi varchar(255) NOT NULL,
	maksetutvuorot int DEFAULT 0,
	vuoro boolean DEFAULT false NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;