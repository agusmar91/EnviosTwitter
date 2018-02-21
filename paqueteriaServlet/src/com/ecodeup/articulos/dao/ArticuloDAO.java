package com.ecodeup.articulos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecodeup.articulos.model.Articulo;
import com.ecodeup.articulos.model.Conexion;

/*
 * @autor: Elivar Largo
 * @web: www.ecodeup.com
 */

public class ArticuloDAO {
	private Conexion con;
	private Connection connection;

	public ArticuloDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar art�culo
	public boolean insertar(Articulo articulo) throws SQLException, ParseException {

		@SuppressWarnings("deprecation")
		
		java.util.Date string=new java.util.Date("10/10/1999");
		java.sql.Date date2 = new java.sql.Date(string.getTime());
		
		String sql = "INSERT INTO articulos (id, origen, destino, paquete, fecha, remitente,transportista,precio) VALUES (?,?,?,?,?,?,?,?)";
		System.out.println(articulo.getPaquete());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, null);
		statement.setString(2, articulo.getOrigen());
		statement.setString(3, articulo.getDestino());
		statement.setString(4, articulo.getPaquete());
		statement.setDate(5, date2);
		statement.setString(6, articulo.getRemitente());
		statement.setString(7, articulo.getTransportista());
		statement.setDouble(8, articulo.getPrecio());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Articulo> listarArticulos() throws SQLException {
		
		List<Articulo> listaArticulos = new ArrayList<Articulo>();
		String sql = "SELECT * FROM articulos";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);

		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String origen = resulSet.getString("origen");
			String destino = resulSet.getString("destino");
			String paquete = resulSet.getString("paquete");
			Date fecha = resulSet.getDate("fecha");
			Double precio = resulSet.getDouble("precio");
			String transportista= resulSet.getString("transportista");
			String remitente = resulSet.getString("remitente");
			Articulo articulo = new Articulo(id, origen,destino,paquete,fecha,remitente,transportista,precio);
			listaArticulos.add(articulo);
		}

		con.desconectar();
		return listaArticulos;
	}

	 //obtener por id
	public Articulo obtenerPorId(int id) throws SQLException {
		Articulo articulo = null;

		String sql = "SELECT * FROM articulos WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			articulo = new Articulo(res.getInt("id"), res.getString("origen"), res.getString("destino"),
					res.getString("paquete"), res.getDate("fecha"), res.getString("remitente"), res.getString("transportista"),Double.parseDouble(res.getString("precio")));
		}
		res.close();
		con.desconectar();

		return articulo;
	}
	
	public List<Articulo> obtenerPorOrigenDestino(String origen1) throws SQLException {
		//Al coger el valor de la base de datos que tiene una coma en medio, solo me coge lo que hay delante de la coma
		
		List<Articulo> listaArticulos = new ArrayList<Articulo>();

		System.out.println("En el dao "+origen1);
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM articulos WHERE origen=?");
		ps.setString(1, origen1);

		ResultSet resulSet = ps.executeQuery();

		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String origen = resulSet.getString("origen");
			String destino = resulSet.getString("destino");
			String paquete = resulSet.getString("paquete");
			Date fecha = resulSet.getDate("fecha");
			Double precio = resulSet.getDouble("precio");
			String transportista= resulSet.getString("transportista");
			String remitente = resulSet.getString("remitente");
			Articulo articulo = new Articulo(id, origen,destino,paquete,fecha,remitente,transportista,precio);
			listaArticulos.add(articulo);
		}

		con.desconectar();
		return listaArticulos;
	}

	 //actualizar
	public boolean actualizar(Articulo articulo) throws SQLException {
		boolean rowActualizar = false;
		java.util.Date string=new java.util.Date("10/10/1999");
		java.sql.Date date = new java.sql.Date(string.getTime());
		String sql = "UPDATE articulos SET id=?,origen=?,destino=?,paquete=?, fecha=?,remitente=?,transportista=?,precio=?    WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, articulo.getId());
		statement.setString(2, articulo.getOrigen());
		statement.setString(3, articulo.getDestino());
		statement.setString(4, articulo.getPaquete());
		statement.setDate(5, date);
		statement.setString(6, articulo.getRemitente());
		statement.setString(7, articulo.getTransportista());
		statement.setDouble(8, articulo.getPrecio());
		statement.setInt(9, articulo.getId());

		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Articulo articulo) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM articulos WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, articulo.getId());

		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}
}