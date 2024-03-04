package com.example.demo.repository.modelo.DTO;

import java.io.Serializable;
import java.time.LocalDate;


public class ReservaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8426243621875890127L;
	private String placa;
	private String modelo;
	private String estado;
	private String fecha;
	private String reservadoPor;


	//GET Y SET
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getReservadoPor() {
		return reservadoPor;
	}
	public void setReservadoPor(String reservadoPor) {
		this.reservadoPor = reservadoPor;
	}




}