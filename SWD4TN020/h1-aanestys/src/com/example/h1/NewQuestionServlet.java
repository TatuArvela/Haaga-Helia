package com.example.h1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewQuestionServlet
 */
@WebServlet("/newquestion")
public class NewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewQuestionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher("newquestion.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String question = (String)request.getParameter("question");
		createQuestion(question);
		
		response.sendRedirect("voting");
	}

	protected void createQuestion(String question) {
		Connection connection = null;
	
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("url"), 
					DBConnectionProperties.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance().getProperty("password"));
		
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO question (question) VALUES (?);");
		pstmt.setString(1, question);
		pstmt.executeQuery();
		
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
