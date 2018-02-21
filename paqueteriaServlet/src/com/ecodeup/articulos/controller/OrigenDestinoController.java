package com.ecodeup.articulos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class OrigenDestinoController
 */
@WebServlet("/OrigenDestinoController")
public class OrigenDestinoController extends HttpServlet {
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
    public OrigenDestinoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/privado/tablaPedidos.jsp");
		String origen =request.getParameter("inicio");
		String destino =request.getParameter("destino");
		List<Articulo> listaArticulos = null;
		System.out.println("En el controlador"+origen);
			try {
				listaArticulos = articuloDAO.obtenerPorOrigenDestino(origen,destino);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
		request.setAttribute("lista", listaArticulos);
	
		dispatcher.forward(request, response);
	}

}
