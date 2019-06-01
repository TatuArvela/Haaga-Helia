package com.example.viikkotentti3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.viikkotentti3.kello.GmtIsoKello;
import com.example.viikkotentti3.kello.InternetKelloLahde;
import com.example.viikkotentti3.kello.Kello;

/**
 * Servlet implementation class KelloServlet
 */
@WebServlet("/kello")
public class KelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public KelloServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Kello kello = new GmtIsoKello(new InternetKelloLahde());

		
		request.setAttribute("pvm", kello.getPvm());
		request.setAttribute("aika", kello.getAika());
		request.getRequestDispatcher("WEB-INF/jsp/kello.jsp").forward(request, response);
	}

}
