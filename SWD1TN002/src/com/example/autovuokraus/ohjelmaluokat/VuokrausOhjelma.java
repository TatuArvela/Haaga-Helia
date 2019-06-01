package com.example.autovuokraus.ohjelmaluokat;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.autovuokraus.kohdeluokat.*;
import com.example.autovuokraus.tietokanta.DAO;

@WebServlet("/VuokrausOhjelma")
public class VuokrausOhjelma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VuokrausOhjelma() {
        super();
    }
    
    // GET
    // Suorittaa valitun toiminnon
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher disp;
		
		if (action == null) {
			disp = request.getRequestDispatcher("index.jsp");
			disp.forward(request, response);
		}
		else if (action.equalsIgnoreCase("hae kaikki vuokraukset")){
			haeKaikkiVuokraukset(request, response);
		}
		else if (action.equalsIgnoreCase("hae vuokraukset päivältä")){
			haePaivanVuokraukset(request, response);
		}
		else if (action.equalsIgnoreCase("lisää vuokraus")){
			avaaUusiVuokraus(request, response);
		}
	}
	
	// POST
	// Siirtyy suoraan ainoaan toimintoon
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		teeUusiVuokraus(request, response);
	}
	
	// Hae kaikki vuokraukset
	// Noutaa kaikki vuokraukset tietokannasta vuokraukset-näkymään
	private void haeKaikkiVuokraukset(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		RequestDispatcher disp;
		try {
			List<Vuokraus> lista = dao.haeVuokraukset();
			
			request.setAttribute("vuokraukset", lista);
			
			disp = request.getRequestDispatcher("vuokraukset.jsp");
			disp.forward(request, response);
		} catch (Exception e) {}
	}
	
	// Hae vuokraukset päivältä
	// Noutaa tiettynä päivänä tehdyt vuokraukset tietokannasta vuokraukset-näkymään
	private void haePaivanVuokraukset(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		RequestDispatcher disp;
		String date = request.getParameter("date");
		try {
			List<Vuokraus> lista = dao.haeVuokraukset(date);
			
			request.setAttribute("vuokraukset", lista);
			
			disp = request.getRequestDispatcher("vuokraukset.jsp");
			disp.forward(request, response);
		} catch (Exception e) {}
	}
	
	// Lisää vuokraus
	// Avaa uusivuokraus-näkymän ja noutaa lomakkeen vaihtoehdot
	private void avaaUusiVuokraus(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		RequestDispatcher disp;
		try {
			List<Asiakas> asiakaslista = dao.haeAsiakkaat();
			List<Auto> autolista = dao.haeAutot();
			
			request.setAttribute("asiakkaat", asiakaslista);
			request.setAttribute("autot", autolista);
			
			disp = request.getRequestDispatcher("uusivuokraus.jsp");
			disp.forward(request, response);
		} catch (Exception e) {}
	}
	
	// Lähettää uuden vuokrauksen tiedot tietokantaan
	private void teeUusiVuokraus(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		RequestDispatcher disp;
		try {
			String vuokrauspvm = request.getParameter("vuokrauspvm");
			String paattymispvm = request.getParameter("paattymispvm");
			String kokonaishinta = request.getParameter("kokonaishinta");
			String vuokraaja = request.getParameter("asiakastunnus");
			String vuokrakohde = request.getParameter("rekno");
			String maksupvm = request.getParameter("maksupvm");
			
			dao.lisaaVuokraus(vuokrauspvm, paattymispvm, kokonaishinta, vuokraaja, vuokrakohde, maksupvm);

			disp = request.getRequestDispatcher("index.jsp");
			disp.forward(request, response);
		} catch (Exception e) {}
	}
}