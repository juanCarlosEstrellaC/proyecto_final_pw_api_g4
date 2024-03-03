package com.example.demo.repository.modelo;

import java.time.LocalDate;

public class DatoReserva {

	private String placa;
	private String cedula;
	private String tarjeta;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public DatoReserva() {

	}

	public DatoReserva(String placa, String cedula, String tarjeta, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.placa = placa;
		this.cedula = cedula;
		this.tarjeta = tarjeta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}