package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Factura;

public class FacturaCtrl implements Control<Factura> {

	private Conexion conexion;

	public FacturaCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public String emitir(Factura factura) throws Throwable {
		ResultSet rs;

		conexion.SQL(
				"Select nombres,apellidos,NIT,(habitacion.precio+regimen.precio) as Monto, fechaPartida from registroactividad inner join hospedaje on registroactividad.codigoHospedaje = hospedaje.codigoHospedaje inner join huesped on hospedaje.codigoHuesped = huesped.codigoHuesped inner join habitacion on hospedaje.codigoHabitacion = habitacion.codigoHabitacion inner join regimen on hospedaje.codigoRegimen = regimen.codigoRegimen");

		rs = conexion.resultSet();

		String descripcion = "";

		while (rs.next()) {
			descripcion = "Nombres: " + rs.getString("nombres") + " " + rs.getString("apellidos") + " NIT: "
					+ rs.getString("NIT") + " Monto: " + rs.getString("Monto") + " Fecha: "
					+ rs.getDate("fechaPartida");
		}

		rs.close();

		return descripcion;
	}

	@Override
	public void insert(Factura factura) throws Throwable {
		conexion.SQL("Insert into factura(codigoFactura,descripcion) VALUES(?,?)");
		conexion.preparedStatement().setInt(1, factura.getCodigoFactura());
		conexion.preparedStatement().setString(2, factura.getDescripcion());
		conexion.CUD();
	}

	@Override
	public ArrayList<Factura> list() throws Throwable {
		ArrayList<Factura> lista = new ArrayList<Factura>();
		ResultSet rs;

		conexion.SQL("Select * from factura");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigoFactura = rs.getInt("codigoFactura");
			String descripcion = rs.getString("descripcion");
			lista.add(new Factura(codigoFactura, descripcion));
		}
		return lista;
	}

	@Override
	public void search(Factura factura) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from factura where codigoFactura=?");
		conexion.preparedStatement().setString(1, factura.getDescripcion());
		rs = conexion.resultSet();

		while (rs.next()) {
			factura.setDescripcion(rs.getString("descripcion"));
		}

		rs.close();
	}

	@Override
	public void update(Factura factura) throws Throwable {
		if (factura != null) {
			String descripcion = factura.getDescripcion();

			conexion.SQL("Update registroactividad set descripcion = ? where codigo=?");
			conexion.preparedStatement().setString(1, descripcion);
			conexion.preparedStatement().setInt(2, factura.getCodigoFactura());
			conexion.CUD();
		}
	}

}
