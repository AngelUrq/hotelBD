package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Regimen;

public class RegimenCtrl implements Control<Regimen> {

	private Conexion conexion;

	public RegimenCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Regimen> list() throws Throwable {
		ArrayList<Regimen> habitacion = new ArrayList<Regimen>();
		ResultSet rs;
		conexion.SQL("Select * from regimen");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigoRegimen = rs.getInt("codigoRegimen");
			String descripcion = rs.getString("descripcion");
			double precio = rs.getDouble("precio");

			habitacion.add(new Regimen(codigoRegimen, descripcion, precio));
		}

		return habitacion;
	}

	public void insert(Regimen regimen) throws Throwable {
		conexion.SQL("Insert into regimen(descripcion, precio) VALUES(?,?)");
		conexion.preparedStatement().setString(1, regimen.getDescripcion());
		conexion.preparedStatement().setDouble(2, regimen.getPrecio());
		conexion.CUD();
	}

	public void search(Regimen regimen) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from regimen where codigoRegimen=?");
		conexion.preparedStatement().setInt(1, regimen.getCodigoRegimen());
		rs = conexion.resultSet();

		while (rs.next()) {
			regimen.setDescripcion(rs.getString("descripcion"));
			regimen.setPrecio(rs.getDouble("precio"));
		}
		rs.close();

	}

	public void update(Regimen regimen) throws Throwable {
		if (regimen != null) {
			int codigoRegimen = regimen.getCodigoRegimen();
			String descripcion = regimen.getDescripcion();
			double precio = regimen.getPrecio();

			conexion.SQL("Update regimen set descripcion=?, precio=? where codigoRegimen=?");
			conexion.preparedStatement().setString(1, descripcion);
			conexion.preparedStatement().setDouble(2, precio);
			conexion.preparedStatement().setInt(3, codigoRegimen);
			conexion.CUD();
		}

	}

}
