package com.hotel.entity;

public class RegistroActividad {

	private int codigoRegistro;
	private int codigoHospedaje;
	private int codigoEmpleado;

	public RegistroActividad(int codigoRegistro, int codigoHospedaje, int codigoEmpleado) {
		this.codigoRegistro = codigoRegistro;
		this.codigoHospedaje = codigoHospedaje;
		this.codigoEmpleado = codigoEmpleado;
	}

	public RegistroActividad(int codigoHospedaje, int codigoEmpleado) {
		this.codigoHospedaje = codigoHospedaje;
		this.codigoEmpleado = codigoEmpleado;
	}

	public int getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(int codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public int getCodigoHospedaje() {
		return codigoHospedaje;
	}

	public void setCodigoHospedaje(int codigoHospedaje) {
		this.codigoHospedaje = codigoHospedaje;
	}

	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	@Override
	public String toString() {
		return "RegistroActividad [codigoRegistro = " + codigoRegistro + ", codigoHospedaje = " + codigoHospedaje
				+ ", codigoEmpleado = " + codigoEmpleado + "]";
	}

}
