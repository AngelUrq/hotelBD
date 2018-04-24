package com.hotel.control;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Hospedaje;

public class HospedajeCtrl implements Control<Hospedaje> {

	private Conexion conexion;

	public HospedajeCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	@Override
	public ArrayList<Hospedaje> list() throws Throwable {
		ArrayList<Hospedaje> hospedaje = new ArrayList<Hospedaje>();
		ResultSet rs;
		conexion.SQL("Select * from hospedaje");

		rs = conexion.resultSet();

		while (rs.next()) {

			int codigoHospedaje = rs.getInt("codigoHospedaje");
			int codigoHuesped = rs.getInt("codigoHuesped");
			int codigoRegimen = rs.getInt("codigoRegimen");
			int codigoHabitacion = rs.getInt("codigoHabitacion");
			Date fechaLlegada = rs.getDate("fechaLlegada");
			Date fechaPartida = rs.getDate("fechaPartida");

			hospedaje.add(new Hospedaje(codigoHospedaje, codigoHuesped, codigoHabitacion, codigoRegimen, fechaLlegada,
					fechaPartida));
		}

		return hospedaje;
	}

	@Override
	public void insert(Hospedaje hospedaje) throws Throwable {
		conexion.SQL(
				"Insert into hospedaje(codigoHuesped, codigoHabitacion, codigoRegimen, fechaLlegada, fechaPartida) VALUES(?,?,?,?,?)");
		conexion.preparedStatement().setInt(1, hospedaje.getCodigoHuesped());
		conexion.preparedStatement().setInt(2, hospedaje.getCodigoHuesped());
		conexion.preparedStatement().setInt(3, hospedaje.getCodigoRegimen());
		conexion.preparedStatement().setDate(4, hospedaje.getFechaLlegada());
		conexion.preparedStatement().setDate(5, hospedaje.getFechaPartida());
		conexion.CUD();
	}

	@Override
	public void search(Hospedaje hospedaje) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from hospedaje where codigoHospedaje=?");
		conexion.preparedStatement().setInt(1, hospedaje.getCodigoHospedaje());
		rs = conexion.resultSet();

		while (rs.next()) {
			hospedaje.setCodigoHuesped(rs.getInt("codigoHuesped"));
			hospedaje.setCodigoHabitacion(rs.getInt("codigoHabitacion"));
			hospedaje.setCodigoRegimen(rs.getInt("codigoRegimen"));
			hospedaje.setFechaLlegada(rs.getDate("fechaLlegada"));
			hospedaje.setFechaPartida(rs.getDate("fechaPartida"));
		}
		rs.close();
	}

	@Override
	public void update(Hospedaje hospedaje) throws Throwable {

		if (hospedaje != null) {
			int codigoHospedaje = hospedaje.getCodigoHospedaje();
			int codigoHuesped = hospedaje.getCodigoHuesped();
			int codigoHabitacion = hospedaje.getCodigoHabitacion();
			int codigoRegimen = hospedaje.getCodigoRegimen();
			Date fechaLlegada = hospedaje.getFechaLlegada();
			Date fechaPartida = hospedaje.getFechaPartida();

			conexion.SQL(
					"Update huesped set codigoHuesped = ?, codigoHabitacion = ?, codigoRegimen = ?, fechaLlegada = ?, fechaPartida = ? where codigoHospedaje=?");
			conexion.preparedStatement().setInt(1, codigoHuesped);
			conexion.preparedStatement().setInt(2, codigoHabitacion);
			conexion.preparedStatement().setInt(3, codigoRegimen);
			conexion.preparedStatement().setDate(4, fechaLlegada);
			conexion.preparedStatement().setDate(5, fechaPartida);
			conexion.preparedStatement().setInt(6, codigoHospedaje);
			conexion.CUD();
		}
	}

}
