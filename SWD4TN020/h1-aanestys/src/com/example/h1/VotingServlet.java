package com.example.h1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VotingServlet
 */
@WebServlet("/voting")
public class VotingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotingServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		String question = null;
		int yes = 1;
		int no = 1;
		
		String vote = request.getParameter("vote");
		if (vote != null) {
			if (vote.equals("yes") || vote.equals("no")) {
				doVote(vote);
				disp = request.getRequestDispatcher("voting.jsp?vote="+vote);
			}
			else {
				disp = request.getRequestDispatcher("voting.jsp");
			}
		}
		else {
			disp = request.getRequestDispatcher("voting.jsp");
		}
	
		Connection connection = null;
	
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"), 
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		
		String sql = "SELECT question, yes, no "
				+ "FROM question "
				+ "WHERE id=(SELECT max(id) FROM question)";
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(sql);
		
		while(resultset.next()) {
			question = resultset.getString("question");
			yes = resultset.getInt("yes");
			no = resultset.getInt("no");
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch(Exception e) {
				System.out.println("The database connection will not close");
			}
		}
		
		request.setAttribute("question", question);
		request.setAttribute("yes", yes);
		request.setAttribute("no", no);
		
		disp.forward(request, response);
	}

	protected void doVote(String vote) {
		Connection connection = null;
		String sql;
	
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"), 
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		
		if (vote.equals("yes")) {
			sql = "UPDATE question "
					+ "SET yes = yes+1 "
					+ "WHERE id = (SELECT max(id) FROM (SELECT * FROM question) AS something)";
		}
		else {
			sql = "UPDATE question "
					+ "SET no = no+1 "
					+ "WHERE id = (SELECT max(id) FROM (SELECT * FROM question) AS something)";
		}
		
		Statement statement = connection.createStatement();
		statement.executeQuery(sql);
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch(Exception e) {
				System.out.println("The database connection will not close");
			}
		}
	}
}
