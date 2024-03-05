package com.example.demo.repository.modelo.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.repository.modelo.Reserva;

public class VehiculoDTO extends RepresentationModel<VehiculoDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5226029326240786334L;
	// Vehiculo
	private String placa;
	private String modelo;
	private String marca;
	private String anio;
	private String estado;
	private BigDecimal renta;
	private List<Reserva> reservas;

	public VehiculoDTO() {

	}

	public VehiculoDTO(String placa, String modelo, String marca, String anio, String estado, BigDecimal renta,
			List<Reserva> reservas) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anio = anio;
		this.estado = estado;
		this.renta = renta;
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "VehiculoDTO [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", anio=" + anio
				+ ", estado=" + estado + ", renta=" + renta + ", reservas=" + reservas + "]";
	}

	// GET Y SET
	public String getPlaca() {
		return placa;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getRenta() {
		return renta;
	}

	public void setRenta(BigDecimal renta) {
		this.renta = renta;
	}

}
