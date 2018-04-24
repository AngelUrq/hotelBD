package com.hotel.entity;

public class Huesped {

	private int codigoHuesped;
	private String nombres;
	private String apellidos;
	private int telefono;
	private String direccion;
	private String NIT;

	public Huesped(int codigoHuesped, String nombres, String apellidos, int telefono, String direccion, String NIT) {
		this.codigoHuesped = codigoHuesped;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.NIT = NIT;
	}

	public Huesped(String nombres, String apellidos, int telefono, String direccion, String NIT) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.NIT = NIT;
	}

	public Huesped(int codigo) {
		this.codigoHuesped = codigo;
	}

	public int getCodigoHuesped() {
		return codigoHuesped;
	}

	public void setCodigoHuesped(int codigoHuesped) {
		this.codigoHuesped = codigoHuesped;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	@Override
	public String toString() {
		return "Código = " + codigoHuesped + ", nombres = " + nombres + ", apellidos = " + apellidos + ", dirección = "
				+ direccion + ", NIT = " + NIT + "";
	}

}
