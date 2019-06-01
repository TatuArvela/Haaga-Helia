package com.example.autovuokraus.tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.autovuokraus.kohdeluokat.*;

public class DAO {

	private Connection yhdista() throws SQLException {
		Connection connection = null;

		try {
			// Asetusten haku
			String ajuri = DBConnectionProperties.getInstance().getProperty("driver");
			String kayttajatunnus = DBConnectionProperties.getInstance().getProperty("username");
			String salasana = DBConnectionProperties.getInstance().getProperty("password");
			String url = DBConnectionProperties.getInstance().getProperty("url");

			// Ajurin määritys
			Class.forName(ajuri).newInstance();

			// Tietokantayhteyden määritys
			connection = DriverManager.getConnection(url, kayttajatunnus, salasana);
		} catch (SQLException sqlE) {
			System.err.println(
					"Tietokantayhteyden avaaminen ei onnistunut\n" + sqlE.getMessage() + " " + sqlE.toString());
			sqlE.printStackTrace();
			throw (sqlE);
		} catch (Exception e) {
			System.err.println("JDBC:n ajuria ei löydy tai sen käynnistys epäonnistui\n" + e.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw new SQLException();
		}

		return connection;
	}

	public List<Vuokraus> haeVuokraukset() throws SQLException {
		Vuokraus vuokraus = null;
		Asiakas asiakas;
		Auto auto;
		List<Vuokraus> lista = null;

		// Suoritettava SQL-kysely
		String sql = "SELECT numero, vuokrauspvm, paattymispvm, kokonaishinta, "
				+ "maksupvm, id, etunimi, sukunimi, osoite, asiakas.postinro, postitmp, "
				+ "puhelin, rekno, merkki, malli, huoltopvm, vrkhinta "
				+ "FROM vuokraus JOIN asiakas ON vuokraaja=id JOIN posti ON "
				+ "asiakas.postinro= posti.postinro JOIN auto ON rekno=vuokrakohde " + "ORDER BY numero";
		PreparedStatement preparedStatement = null;

		// SQL-kyselyn tulokset
		ResultSet tulosjoukko = null;
		Connection conn = null;

		try {
			conn = yhdista();
			if (conn != null) {
				// Aloita transaktio
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

				// Valmistele SQL-lause
				preparedStatement = conn.prepareStatement(sql);

				// Toteuta SQL-lause ja kerää sen tulokset
				tulosjoukko = preparedStatement.executeQuery();

				// Päätä transaktio
				preparedStatement.close();
				conn.commit();
				conn.close();

				if (tulosjoukko != null) {
					while (tulosjoukko.next()) {
						vuokraus = teeVuokraus(tulosjoukko);
						asiakas = teeAsiakas(tulosjoukko);
						vuokraus.setVuokraaja(asiakas);
						auto = teeAuto(tulosjoukko);
						vuokraus.setVuokrakohde(auto);

						if (lista == null) {
							lista = new ArrayList<Vuokraus>();
						}

						lista.add(vuokraus);
					}

					tulosjoukko.close();
				} else {
					lista = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException();
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.rollback(); // Peruuta transaktio
					conn.close();
				} catch (Exception e) {
					throw new SQLException();
				}
			}
		}

		return lista;
	}

	public List<Vuokraus> haeVuokraukset(String pvm) throws SQLException {
		Vuokraus vuokraus = null;
		Asiakas asiakas;
		Auto auto;
		List<Vuokraus> lista = null;

		// Suoritettava SQL-kysely
		String sql = "SELECT numero, vuokrauspvm, paattymispvm, kokonaishinta, "
				+ "maksupvm, id, etunimi, sukunimi, osoite, asiakas.postinro, postitmp, "
				+ "puhelin, rekno, merkki, malli, huoltopvm, vrkhinta "
				+ "FROM vuokraus JOIN asiakas ON vuokraaja=id JOIN posti ON "
				+ "asiakas.postinro=posti.postinro JOIN auto ON rekno=vuokrakohde " + "WHERE vuokrauspvm=? "
				+ "ORDER BY numero";
		PreparedStatement preparedStatement = null;

		// SQL-kyselyn tulokset
		ResultSet tulosjoukko = null;
		Connection conn = null;

		try {
			conn = yhdista();
			if (conn != null) {
				// Aloita transaktio
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

				// Valmistele SQL-lause
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, pvm);

				// Toteuta SQL-lause
				tulosjoukko = preparedStatement.executeQuery();

				// Päätä transaktio
				preparedStatement.close();
				conn.commit();
				conn.close();

				if (tulosjoukko != null) {
					while (tulosjoukko.next()) {
						vuokraus = teeVuokraus(tulosjoukko);
						asiakas = teeAsiakas(tulosjoukko);
						vuokraus.setVuokraaja(asiakas);
						auto = teeAuto(tulosjoukko);
						vuokraus.setVuokrakohde(auto);

						if (lista == null) {
							lista = new ArrayList<Vuokraus>();
						}

						lista.add(vuokraus);
					}

					tulosjoukko.close();
				} else {
					lista = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException();
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.rollback(); // Peruuta transaktio
					conn.close();
				} catch (Exception e) {
					throw new SQLException();
				}
			}
		}

		return lista;
	}

	public List<Asiakas> haeAsiakkaat() throws SQLException {
		Asiakas asiakas;
		List<Asiakas> lista = null;

		// Suoritettava SQL-kysely
		String sql = "SELECT id, etunimi, sukunimi, osoite, " + "puhelin, asiakas.postinro, postitmp "
				+ "FROM asiakas JOIN posti ON " + "asiakas.postinro = posti.postinro " + "ORDER BY sukunimi";
		PreparedStatement preparedStatement = null;

		// SQL-kyselyn tulokset
		ResultSet tulosjoukko = null;
		Connection conn = null;

		try {
			conn = yhdista();
			if (conn != null) {
				// Aloita transaktio
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

				// Valmistele SQL-lause
				preparedStatement = conn.prepareStatement(sql);

				// Toteuta SQL-lause
				tulosjoukko = preparedStatement.executeQuery();

				// Päätä transaktio
				preparedStatement.close();
				conn.commit();
				conn.close();

				if (tulosjoukko != null) {
					while (tulosjoukko.next()) {
						asiakas = teeAsiakas(tulosjoukko);

						if (lista == null) {
							lista = new ArrayList<Asiakas>();
						}

						lista.add(asiakas);
					}

					tulosjoukko.close();
				} else {
					lista = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException();
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.rollback(); // Peruuta transaktio
					conn.close();
				} catch (Exception e) {
					throw new SQLException();
				}
			}
		}

		return lista;
	}

	public List<Auto> haeAutot() throws SQLException {
		Auto auto;
		List<Auto> lista = null;

		// Suoritettava SQL-kysely
		String sql = "SELECT rekno, malli, merkki, vrkhinta, huoltopvm " + "FROM auto " + "ORDER BY merkki";
		PreparedStatement preparedStatement = null;

		// SQL-kyselyn tulokset
		ResultSet tulosjoukko = null;
		Connection conn = null;

		try {
			conn = yhdista();
			if (conn != null) {
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

				preparedStatement = conn.prepareStatement(sql);
				tulosjoukko = preparedStatement.executeQuery();

				preparedStatement.close();
				conn.commit();
				conn.close();

				if (tulosjoukko != null) {
					while (tulosjoukko.next()) {
						auto = teeAuto(tulosjoukko);

						if (lista == null) {
							lista = new ArrayList<Auto>();
						}

						lista.add(auto);
					}

					tulosjoukko.close();
				} else {
					lista = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException();
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.rollback(); // Peruuta transaktio
					conn.close();
				} catch (Exception e) {
					throw new SQLException();
				}
			}
		}

		return lista;
	}

	// Lisää uusi vuokraus tietokantaan (vuokrausta ei ole maksettu)
	public void lisaaVuokraus(String vuokrauspvm, String paattymispvm, String kokonaishinta, String vuokraaja,
			String vuokrakohde, String maksupvm) throws SQLException {
		// Suoritettava SQL-kysely
		String sql = "INSERT INTO vuokraus (vuokrauspvm, paattymispvm, " + "kokonaishinta, vuokraaja, vuokrakohde) "
				+ "VALUES (?,?,?,?,?)";
		if (maksupvm != null) {
			sql = "INSERT INTO vuokraus (vuokrauspvm, paattymispvm, "
					+ "kokonaishinta, vuokraaja, vuokrakohde, maksupvm) " + "VALUES (?,?,?,?,?,?)";
		}

		System.out.println(sql);

		// Suoritettava SQL-lause
		PreparedStatement preparedStatement = null;
		Connection conn = null;

		try {
			conn = yhdista();
			if (conn != null) {
				// Aloita transaktio

				// Valmistele SQL-lause
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, vuokrauspvm);
				preparedStatement.setString(2, paattymispvm);
				preparedStatement.setString(3, kokonaishinta);
				preparedStatement.setString(4, vuokraaja);
				preparedStatement.setString(5, vuokrakohde);
				if (maksupvm != null) {
					preparedStatement.setString(6, maksupvm);
				}

				// Toteuta SQL-lause
				preparedStatement.executeQuery();

				// Päätä transaktio
				preparedStatement.close();
				conn.commit();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new SQLException();
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.rollback(); // Peruuta transaktio
					conn.close();
				} catch (Exception e) {
					throw new SQLException();
				}
			}
		}
	}

	// Tee kohdeluokat
	private Vuokraus teeVuokraus(ResultSet tulosjoukko) throws SQLException {
		Vuokraus vuokraus = null;
		int numero;
		double kokonaishinta;
		Date vuokrauspvm, paattymispvm, maksupvm;

		if (tulosjoukko != null) {
			try {
				numero = tulosjoukko.getInt("numero");
				vuokrauspvm = tulosjoukko.getDate("vuokrauspvm");
				paattymispvm = tulosjoukko.getDate("paattymispvm");
				maksupvm = tulosjoukko.getDate("maksupvm");
				kokonaishinta = tulosjoukko.getDouble("kokonaishinta");
				vuokraus = new Vuokraus(numero, vuokrauspvm, paattymispvm, kokonaishinta, maksupvm, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return vuokraus;
	}

	private Asiakas teeAsiakas(ResultSet tulosjoukko) throws SQLException {
		Asiakas asiakas = null;
		int numero;
		String etunimi, sukunimi, osoite, puhelin;
		String postinro, postitmp;
		Posti posti;

		if (tulosjoukko != null) {
			try {
				numero = tulosjoukko.getInt("id");
				etunimi = tulosjoukko.getString("etunimi");
				sukunimi = tulosjoukko.getString("sukunimi");
				osoite = tulosjoukko.getString("osoite");
				puhelin = tulosjoukko.getString("puhelin");
				postinro = tulosjoukko.getString("postinro");
				postitmp = tulosjoukko.getString("postitmp");

				posti = new Posti(postinro, postitmp);
				asiakas = new Asiakas(numero, etunimi, sukunimi, osoite, puhelin, posti);

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return asiakas;
	}

	private Auto teeAuto(ResultSet tulosjoukko) throws SQLException {
		Auto auto = null;
		String rekno, malli, merkki;
		double vrkhinta;
		Date huoltopvm;

		if (tulosjoukko != null) {
			try {
				rekno = tulosjoukko.getString("rekno");
				malli = tulosjoukko.getString("malli");
				merkki = tulosjoukko.getString("merkki");
				vrkhinta = tulosjoukko.getDouble("vrkhinta");
				huoltopvm = tulosjoukko.getDate("huoltopvm");

				auto = new Auto(rekno, malli, merkki, vrkhinta, huoltopvm);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return auto;
	}
}
