package com.hotel.entity;

public class Regimen {

	private int codigoRegimen;
	private String descripcion;
	private double precio;

	public Regimen(int codigoRegimen, String descripcion, double precio) {
		this.codigoRegimen = codigoRegimen;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Regimen(String descripcion, double precio) {
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Regimen(int codigo3) {
		this.codigoRegimen = codigo3;
	}

	public int getCodigoRegimen() {
		return codigoRegimen;
	}

	public void setCodigoRegimen(int codigoRegimen) {
		this.codigoRegimen = codigoRegimen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "CodigoRegimen = " + codigoRegimen + ", descripcion = " + descripcion + ", precio = " + precio;
	}

}
