package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.RegistroActividad;

public class RegistroActividadCtrl {
	private Conexion conexion;

	public RegistroActividadCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<RegistroActividad> list() throws Throwable {
		ArrayList<RegistroActividad> lista = new ArrayList<RegistroActividad>();
		ResultSet rs;

		conexion.SQL("Select * from registroactividad");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigoRegistro = rs.getInt("codigoRegistro");
			int codigoHospedaje = rs.getInt("codigoHospedaje");
			int codigoEmpleado = rs.getInt("codigoEmpleado");

			lista.add(new RegistroActividad(codigoRegistro, codigoHospedaje, codigoEmpleado));
		}
		return lista;
	}

	public void insert(RegistroActividad r) throws Throwable {
		conexion.SQL("Insert into registroactividad(codigoHospedaje,codigoEmpleado) VALUES(?,?)");
		conexion.preparedStatement().setInt(1, r.getCodigoHospedaje());
		conexion.preparedStatement().setInt(2, r.getCodigoEmpleado());
		conexion.CUD();
	}

	public void search(RegistroActividad r) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from registroactividad where codigoRegistro=?");
		conexion.preparedStatement().setInt(1, r.getCodigoRegistro());
		rs = conexion.resultSet();

		while (rs.next()) {
			r.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
			r.setCodigoHospedaje(rs.getInt("codigoHospedaje"));
		}

		rs.close();
	}

	public void update(RegistroActividad r) throws Throwable {
		int codigoEmpleado = r.getCodigoEmpleado();
		int codigoHospedaje = r.getCodigoHospedaje();

		conexion.SQL("Update registroactividad set codigoEmpleado = ?, codigoHospedaje = ? where codigoRegistro=?");
		conexion.preparedStatement().setInt(1, codigoEmpleado);
		conexion.preparedStatement().setInt(2, codigoHospedaje);
		conexion.preparedStatement().setInt(3, r.getCodigoRegistro());
		conexion.CUD();
	}

}
