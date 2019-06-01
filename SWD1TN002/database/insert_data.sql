INSERT INTO posti (postinro, postitmp)
VALUES ( "00550", "HELSINKI");

INSERT INTO posti (postinro, postitmp)
VALUES ( "00640", "HELSINKI");

INSERT INTO asiakas (id, etunimi, sukunimi, osoite, puhelin, postinro)
VALUES(10000, "Risto", "Reipas", "Reippaankatu 5 B", "12345678", "00550");

INSERT INTO asiakas (id, etunimi, sukunimi, osoite, puhelin, postinro)
VALUES(10100, "Ulla", "Ulpukka", "Ulpukankatu 7 B", "124578787", "00550");

INSERT INTO asiakas (id, etunimi, sukunimi, osoite, puhelin, postinro)
VALUES(10200, "Leif", "Fager", "Fagerinkatu 3 A", "8989989", "00640");

INSERT INTO auto (rekno, malli, merkki, vrkhinta, huoltopvm)
VALUES ("BMA-673", "Golf", "Volkswagen", 120.00, "2016-01-01");

INSERT INTO auto (rekno, malli, merkki, vrkhinta, huoltopvm)
VALUES ("LBY-457", "Golf", "Volkswagen", 160.00, null);

INSERT INTO auto (rekno, malli, merkki, vrkhinta, huoltopvm)
VALUES ("BU-40", "Record", "Opel", 59.00, "2016-02-01");

INSERT INTO auto (rekno, malli, merkki, vrkhinta, huoltopvm)
VALUES ("ACO-332", "V74", "Volvo", 45.00, "2016-03-01");

INSERT INTO vuokraus (vuokrauspvm, paattymispvm, kokonaishinta, maksupvm, vuokraaja, vuokrakohde)
VALUES ("2016-01-16", "2016-01-20", 480.00, "2016-01-20", 10000, "BMA-673");

INSERT INTO vuokraus (vuokrauspvm, paattymispvm, kokonaishinta, maksupvm, vuokraaja, vuokrakohde)
VALUES ("2016-02-16", "2016-02-20", 640.00, "2016-02-20", 10100, "LBY-457");