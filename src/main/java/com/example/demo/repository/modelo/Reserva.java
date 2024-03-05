package com.example.demo.repository.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@Column(name = "rese_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rese_id_seq")
	@SequenceGenerator(name = "rese_id_seq", sequenceName = "rese_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "rese_numero_reserva")
	private String numeroReserva;

	@Column(name = "rese_numero_cedula")
	private String numero;

	@Column(name = "rese_dias")
	private Integer diasReserva;

	@Column(name = "rese_fecha_inicio")
	private LocalDate fechaInicio;

	@Column(name = "rese_fecha_fin")
	private LocalDate fechaFin;

	@Column(name = "rese_estado")
	private String estado;

	// Relacion muchos a uno con cliente
	@ManyToOne
	@JoinColumn(name = "rese_clie_id")
	@JsonIgnore
	private Cliente cliente;

	// Relacion muchos a uno con vehiculo
	@ManyToOne
	@JoinColumn(name = "rese_vehi_id")
	@JsonIgnore
	private Vehiculo vehiculo;

	// Relacion uno a uno con cobro
	@OneToOne(mappedBy = "reserva", fetch = FetchType.EAGER)
	@JsonIgnore
	private Cobro cobro;

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public Integer getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(Integer diasReserva) {
		this.diasReserva = diasReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", numeroReserva=" + numeroReserva + ", numero=" + numero + ", diasReserva="
				+ diasReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + "]";
	}

}
