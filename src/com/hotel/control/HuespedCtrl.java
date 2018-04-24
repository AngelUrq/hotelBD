package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Huesped;

public class HuespedCtrl implements Control<Huesped> {

	private Conexion conexion;

	public HuespedCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Huesped> list() throws Throwable {
		ArrayList<Huesped> huesped = new ArrayList<Huesped>();
		ResultSet rs;

		conexion.SQL("Select * from huesped");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigoHuesped = rs.getInt("codigoHuesped");
			String nombres = rs.getString("nombres");
			String apellidos = rs.getString("apellidos");
			int telefono = rs.getInt("telefono");
			String direccion = rs.getString("direccion");
			String NIT = rs.getString("NIT");

			huesped.add(new Huesped(codigoHuesped, nombres, apellidos, telefono, direccion, NIT));
		}
		return huesped;
	}

	public void insert(Huesped h) throws Throwable {
		conexion.SQL("Insert into huesped(nombres, apellidos, telefono, direccion, NIT) VALUES(?,?,?,?,?)");
		conexion.preparedStatement().setString(1, h.getNombres());
		conexion.preparedStatement().setString(2, h.getApellidos());
		conexion.preparedStatement().setInt(3, h.getTelefono());
		conexion.preparedStatement().setString(4, h.getDireccion());
		conexion.preparedStatement().setString(5, h.getNIT());
		conexion.CUD();
	}

	public void search(Huesped huesped) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from huesped where codigoHuesped=?");
		conexion.preparedStatement().setInt(1, huesped.getCodigoHuesped());
		rs = conexion.resultSet();

		while (rs.next()) {

			huesped.setNombres(rs.getString("nombres"));
			huesped.setApellidos(rs.getString("apellidos"));
			huesped.setDireccion(rs.getString("direccion"));
			huesped.setTelefono(rs.getInt("telefono"));
			huesped.setNIT(rs.getString("NIT"));
		}

		rs.close();

	}

	public void update(Huesped huesped) throws Throwable {

		if (huesped != null) {
			int codigoHuesped = huesped.getCodigoHuesped();
			String nombres = huesped.getNombres();
			String apellidos = huesped.getApellidos();
			int telefono = huesped.getTelefono();
			String direccion = huesped.getDireccion();
			String NIT = huesped.getNIT();

			conexion.SQL(
					"Update huesped set nombres = ?, apellidos = ?, telefono = ?, direccion = ?, NIT = ? where codigoHuesped=?");
			conexion.preparedStatement().setString(1, nombres);
			conexion.preparedStatement().setString(2, apellidos);
			conexion.preparedStatement().setInt(3, telefono);
			conexion.preparedStatement().setString(4, direccion);
			conexion.preparedStatement().setString(5, NIT);
			conexion.preparedStatement().setInt(6, codigoHuesped);
			conexion.CUD();
		}

	}

}
