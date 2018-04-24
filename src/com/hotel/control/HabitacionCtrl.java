package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Habitacion;

public class HabitacionCtrl implements Control<Habitacion> {

	private Conexion conexion;

	public HabitacionCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Habitacion> list() throws Throwable {
		ArrayList<Habitacion> habitacion = new ArrayList<Habitacion>();
		ResultSet rs;
		conexion.SQL("Select * from habitacion");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigoHabitacion = rs.getInt("codigoHabitacion");
			String descripcion = rs.getString("descripcion");
			String nombre = rs.getString("nombre");
			double precio = rs.getDouble("precio");
			boolean disponibilidad = rs.getBoolean("disponibilidad");

			habitacion.add(new Habitacion(codigoHabitacion, nombre, descripcion, precio, disponibilidad));
		}

		return habitacion;
	}

	public void insert(Habitacion hab) throws Throwable {
		conexion.SQL("Insert into habitacion(descripcion, precio, disponibilidad, nombre) VALUES(?,?,?,?)");
		conexion.preparedStatement().setString(1, hab.getDescripcion());
		conexion.preparedStatement().setDouble(2, hab.getPrecio());
		conexion.preparedStatement().setBoolean(3, hab.isDisponibilidad());
		conexion.preparedStatement().setString(4, hab.getNombre());
		conexion.CUD();
	}

	public void search(Habitacion habitacion) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from habitacion where codigoHabitacion=?");
		conexion.preparedStatement().setInt(1, habitacion.getCodigoHabitacion());
		rs = conexion.resultSet();

		while (rs.next()) {
			habitacion.setNombre(rs.getString("nombre"));
			habitacion.setDescripcion(rs.getString("descripcion"));
			habitacion.setPrecio(rs.getDouble("precio"));
			habitacion.setDisponibilidad(rs.getBoolean("disponibilidad"));
		}
		rs.close();
	}

	public void update(Habitacion habitacion) throws Throwable {
		if (habitacion != null) {
			int codigoHabitacion = habitacion.getCodigoHabitacion();
			String descripcion = habitacion.getDescripcion();
			String nombre = habitacion.getNombre();
			double precio = habitacion.getPrecio();
			boolean disponibilidad = habitacion.isDisponibilidad();

			conexion.SQL(
					"Update habitacion set descripcion = ?, precio = ?, disponibilidad = ?, nombre = ? where codigoHabitacion=?");
			conexion.preparedStatement().setString(1, descripcion);
			conexion.preparedStatement().setDouble(2, precio);
			conexion.preparedStatement().setBoolean(3, disponibilidad);
			conexion.preparedStatement().setString(4, nombre);
			conexion.preparedStatement().setInt(5, codigoHabitacion);
			conexion.CUD();
		}

	}

	public void cambiarDisponibilidad(Habitacion habitacion) throws Throwable {
		if (habitacion != null) {
			conexion.SQL("Update habitacion set disponibilidad = ? where codigoHabitacion=?");
			conexion.preparedStatement().setBoolean(1, habitacion.isDisponibilidad());
			conexion.preparedStatement().setInt(2, habitacion.getCodigoHabitacion());
			conexion.CUD();
		}
	}

}
