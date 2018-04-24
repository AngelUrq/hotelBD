package com.hotel.control;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hotel.entity.Empleado;

public class EmpleadoCtrl implements Control<Empleado> {

	private Conexion conexion;

	public EmpleadoCtrl(Conexion conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Empleado> list() throws Throwable {
		ArrayList<Empleado> empleado = new ArrayList<Empleado>();
		ResultSet rs;
		conexion.SQL("Select * from empleado");

		rs = conexion.resultSet();

		while (rs.next()) {
			int codigo = rs.getInt("codigoEmpleado");
			double salario = rs.getDouble("salario");
			String nombres = rs.getString("nombres");
			String apellidos = rs.getString("apellidos");
			String direccion = rs.getString("direccion");
			int telefono = rs.getInt("telefono");

			empleado.add(new Empleado(codigo, salario, nombres, apellidos, direccion, telefono));
		}

		return empleado;
	}

	public void insert(Empleado empleado) throws Throwable {
		conexion.SQL("Insert into empleado(salario, nombres, apellidos, direccion, telefono) VALUES(?,?,?,?,?)");
		conexion.preparedStatement().setDouble(1, empleado.getSalario());
		conexion.preparedStatement().setString(2, empleado.getNombres());
		conexion.preparedStatement().setString(3, empleado.getApellidos());
		conexion.preparedStatement().setString(4, empleado.getDireccion());
		conexion.preparedStatement().setInt(5, empleado.getTelefono());
		conexion.CUD();
	}

	public void search(Empleado empleado) throws Throwable {
		ResultSet rs;

		conexion.SQL("Select * from empleado where codigoEmpleado=?");
		conexion.preparedStatement().setInt(1, empleado.getCodigoEmpleado());
		rs = conexion.resultSet();

		while (rs.next()) {
			empleado.setSalario(rs.getDouble("salario"));
			empleado.setNombres(rs.getString("nombres"));
			empleado.setApellidos(rs.getString("apellidos"));
			empleado.setDireccion(rs.getString("direccion"));
			empleado.setTelefono(rs.getInt("telefono"));
		}

		rs.close();
	}

	public void update(Empleado empleado) throws Throwable {
		if (empleado != null) {
			int codigoEmpleado = empleado.getCodigoEmpleado();
			double salario = empleado.getSalario();
			String nombres = empleado.getNombres();
			String apellidos = empleado.getApellidos();
			String direccion = empleado.getDireccion();
			int telefono = empleado.getTelefono();

			conexion.SQL(
					"Update empleado set salario=?, nombres =?, apellidos =?, direccion =?, telefono =? where codigoEmpleado=?");
			conexion.preparedStatement().setDouble(1, salario);
			conexion.preparedStatement().setString(2, nombres);
			conexion.preparedStatement().setString(3, apellidos);
			conexion.preparedStatement().setString(4, direccion);
			conexion.preparedStatement().setInt(5, telefono);
			conexion.preparedStatement().setInt(6, codigoEmpleado);
			conexion.CUD();
		}

	}

}
