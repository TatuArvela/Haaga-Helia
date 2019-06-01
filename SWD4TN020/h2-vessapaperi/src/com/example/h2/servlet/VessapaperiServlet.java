package com.example.h2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.h2.bean.Asukas;
import com.example.h2.dao.AsukasDAO;
import com.example.h2.dao.DAOPoikkeus;


/**
 * Servlet implementation class AsukasServlet
 */
@WebServlet("/vuorot")
public class VessapaperiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VessapaperiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Asukas> asukkaat;
		
		try {
			AsukasDAO aDao = new AsukasDAO();
			aDao.edistaVuoro();
			asukkaat = aDao.haeAsukkaat();
		} catch(DAOPoikkeus e) {
			throw new ServletException(e);
		}
		
		request.setAttribute("asukkaat", asukkaat);
		request.getRequestDispatcher("vuorot.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("maksavuoro") != null) {
			int id = Integer.parseInt(request.getParameter("maksavuoro"));
			
			Asukas h = new Asukas(id);
			
			try {
				AsukasDAO aDao = new AsukasDAO();
				aDao.maksaVuoro(h);
			} catch (DAOPoikkeus e) {
				throw new ServletException(e);
			}
		}
		if (request.getParameter("poistavuoro") != null) {
			int id = Integer.parseInt(request.getParameter("poistavuoro"));
			
			Asukas h = new Asukas(id);
			
			try {
				AsukasDAO aDao = new AsukasDAO();
				aDao.poistaVuoro(h);
			} catch (DAOPoikkeus e) {
				throw new ServletException(e);
			}
		}
		
		response.sendRedirect("vuorot");
	}

}
