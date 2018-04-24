package com.hotel.entity;

public class Empleado {

	private int codigoEmpleado;
	private double salario;
	private String nombres;
	private String apellidos;
	private String direccion;
	private int telefono;

	public Empleado(int codigoEmpleado, double salario, String nombres, String apellidos, String direccion,
			int telefono) {
		this.codigoEmpleado = codigoEmpleado;
		this.salario = salario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Empleado(String nombres, String apellidos, double salario, String direccion, int telefono) {
		this.salario = salario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Empleado(int codigo1) {
		this.codigoEmpleado = codigo1;
	}

	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Código = " + codigoEmpleado + ", nombres = " + nombres + ", apellidos = " + apellidos + ", salario = "
				+ salario + ", direccion = " + direccion + ", telefono = " + telefono;
	}

}
