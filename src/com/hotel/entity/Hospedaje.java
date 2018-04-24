package com.hotel.entity;

import java.sql.Date;

public class Hospedaje {

	private int codigoHospedaje;
	private int codigoHuesped;
	private int codigoHabitacion;
	private int codigoRegimen;
	private Date fechaLlegada;
	private Date fechaPartida;

	public Hospedaje(int codigoHospedaje, int codigoHuesped, int codigoHabitacion, int codigoRegimen, Date fechaLlegada,
			Date fechaPartida) {
		this.codigoHospedaje = codigoHospedaje;
		this.codigoHuesped = codigoHuesped;
		this.codigoHabitacion = codigoHabitacion;
		this.codigoRegimen = codigoRegimen;
		this.fechaLlegada = fechaLlegada;
		this.fechaPartida = fechaPartida;
	}

	public Hospedaje(int codigoHuesped, int codigoHabitacion, int codigoRegimen, Date fechaLlegada, Date fechaPartida) {
		this.setCodigoHuesped(codigoHuesped);
		this.codigoHabitacion = codigoHabitacion;
		this.codigoRegimen = codigoRegimen;
		this.fechaLlegada = fechaLlegada;
		this.fechaPartida = fechaPartida;
	}

	public Hospedaje(int codigo) {
		this.codigoHospedaje = codigo;
	}

	public int getCodigoHospedaje() {
		return codigoHospedaje;
	}

	public void setCodigoHospedaje(int codigoHospedaje) {
		this.codigoHospedaje = codigoHospedaje;
	}

	public int getCodigoHabitacion() {
		return codigoHabitacion;
	}

	public void setCodigoHabitacion(int codigoHabitacion) {
		this.codigoHabitacion = codigoHabitacion;
	}

	public int getCodigoRegimen() {
		return codigoRegimen;
	}

	public void setCodigoRegimen(int codigoRegimen) {
		this.codigoRegimen = codigoRegimen;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public Date getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public int getCodigoHuesped() {
		return codigoHuesped;
	}

	public void setCodigoHuesped(int codigoHuesped) {
		this.codigoHuesped = codigoHuesped;
	}

	@Override
	public String toString() {
		return "CodigoHospedaje = " + codigoHospedaje + ", codigoHabitacion = " + codigoHabitacion
				+ ", codigoRegimen = " + codigoRegimen + ", fechaLlegada = " + fechaLlegada + ", fechaPartida = "
				+ fechaPartida;
	}

}
