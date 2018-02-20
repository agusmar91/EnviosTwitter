package com.ecodeup.articulos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminArticulo")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
//			case "showedit":
//				showEditar(request, response);
//				break;	
//			case "editar":
//				editar(request, response);
//				break;
//			case "eliminar":
//				eliminar(request, response);
//				break;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usu, pass;
        usu = request.getParameter("user");
        pass = request.getParameter("password");
       
        if(usu.equals("admin") && pass.equals("admin") && sesion.getAttribute("usuario") == null){
            //si coincide usuario y password y adem�s no hay sesi�n iniciada
            sesion.setAttribute("usuario", usu);
            //redirijo a p�gina con informaci�n de login exitoso
            response.sendRedirect("adminArticulo?action=mostrar");
        }else{
        	response.sendRedirect("vista/login.jsp");
        	System.out.println("Error de login");
        }
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
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
	
	private Date parse(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		System.out.println("Estor dentro de mostrar");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/privado/mostrar.jsp");
		System.out.println("1");
		List<Articulo> listaArticulos= articuloDAO.listarArticulos();
		System.out.println("3");
		request.setAttribute("lista", listaArticulos);
		System.out.println("2");
		dispatcher.forward(request, response);
		System.out.println("final de mosrar");
	}	
	
//	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
//		request.setAttribute("articulo", articulo);
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
//		dispatcher.forward(request, response);
//	}
	
//	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
//		Articulo articulo = new Articulo(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("existencia")), Double.parseDouble(request.getParameter("precio")));
//		articuloDAO.actualizar(articulo);
//		index(request, response);
//	}
//	
//	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
//		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
//		articuloDAO.eliminar(articulo);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//		dispatcher.forward(request, response);
//		
//	}
}