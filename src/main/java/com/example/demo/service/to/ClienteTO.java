package com.example.demo.service.to;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;

public class ClienteTO extends RepresentationModel<ClienteTO>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840832725507061816L;
	
	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private String numeroCedula;

	private LocalDate fechaNacimiento;

	private String genero;

	private String registro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroCedula() {
		return numeroCedula;
	}

	public void setNumeroCedula(String numeroCedula) {
		this.numeroCedula = numeroCedula;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
	

}
