package com.ecodeup.articulos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class RegistrarController
 */
@WebServlet("/RegistrarController")
public class RegistrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;

	public void init() {
		System.out.println("Conexion");
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Fin de la conexion");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo Get");
		String action = request.getParameter("action");
		try {
		switch (action) {

		case "register":
			System.out.println("entro");
			registrar(request, response);
			break;
		default:
			break;
		}			
	} catch (SQLException e) {
		e.getStackTrace();
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			registrar(request, response);
		} catch (NumberFormatException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NumberFormatException, ParseException {
	//	SimpleDateFormat format = new SimpleDateFormat("YYYY,mm,dd");
	//	Date date = (Date) format.parse(request.getParameter("fecha"));
		
		String string = request.getParameter("fecha");
		DateFormat format = new SimpleDateFormat("yyyy-dd-MM");
		Date date = format.parse(string);
		
		
		Articulo articulo = new Articulo(0, request.getParameter("origen"), request.getParameter("destino"), request.getParameter("paquete"), date, request.getParameter("remitente"),request.getParameter("transportista"), Double.parseDouble(request.getParameter("precio")));
		articuloDAO.insertar(articulo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
