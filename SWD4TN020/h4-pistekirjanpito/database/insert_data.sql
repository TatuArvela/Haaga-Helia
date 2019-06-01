INSERT INTO kayttaja
	(tunnus, salasana, etunimi, sukunimi, onopettaja)
VALUES
	("a1", "qwerty", "Antti", "Asentaja", 0),
	("a2", "asdf", "Pekka", "Palvelinosaaja", 0),
	("a3", "salasana", "Matti", "Mikrotukihenkilö", 0),
	("a4", "passu", "Kosti", "Koodari", 0),
	("jusju", "salainen", "Jukka", "Juslin", 1);
	
INSERT INTO pistetieto
	(opiskelijatunnus, kurssi, tehtava, pisteet)
VALUES
	("a1", "Java", "koe", 50),
	("a1", "Java", "tehtävä 1", 20),
	("a2", "Java", "koe", 49),
	("a2", "Java", "tehtävä", 19),
	("a3", "Java", "koe", 47),
	("a3", "Java", "tehtävä", 33),
	("a4", "Java", "koe", 2),
	("a4", "Python", "koe", 50);