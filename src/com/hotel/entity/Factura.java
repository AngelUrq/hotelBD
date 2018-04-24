package com.hotel.entity;

public class Factura {

	private int codigoFactura;
	private String descripcion;

	public Factura(int codigoFactura, String descripcion) {
		this.codigoFactura = codigoFactura;
		this.descripcion = descripcion;
	}

	public Factura(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(int codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Factura [codigoFactura = " + codigoFactura + ", descripcion = " + descripcion + "]";
	}

}
