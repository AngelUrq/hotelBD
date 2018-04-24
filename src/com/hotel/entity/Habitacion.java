package com.hotel.entity;

public class Habitacion {

	private int codigoHabitacion;
	private String nombre;
	private String descripcion;
	private double precio;
	private boolean disponibilidad;

	public Habitacion(int codigoHabitacion, String nombre, String descripcion, double precio, boolean disponibilidad) {
		this.codigoHabitacion = codigoHabitacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public Habitacion(String descripcion, String nombre, double precio, boolean disponibilidad) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public Habitacion(int codigo2) {
		this.codigoHabitacion = codigo2;
	}

	public int getCodigoHabitacion() {
		return codigoHabitacion;
	}

	public void setCodigoHabitacion(int codigoHabitacion) {
		this.codigoHabitacion = codigoHabitacion;
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

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		String mensaje = "No está disponible";
		if (disponibilidad) {
			mensaje = "Está disponible";
		}

		return "Código = " + codigoHabitacion + "\n    Nombre =" + nombre + "\n    Descripcion = " + descripcion
				+ "\n    Precio = " + precio + "\n    Disponibilidad = " + mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
