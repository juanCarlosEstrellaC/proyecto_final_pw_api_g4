package com.example.demo.service.to;

import java.time.LocalDate;

public class ReporteTO {
   
    private String numeroReserva;
 
    private Integer diasReserva;
 
    private LocalDate fechaInicio;
 
    private LocalDate fechaFin;
 
    private String estado;
 
    private String apellido;
   
    private String numeroCedula;
 
    private String placa;
   
    private String modelo;
   
    private String marca;
 
 
    //get y set
 
    public String getNumeroReserva() {
        return numeroReserva;
    }
 
    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }
 
    public Integer getDiasReserva() {
        return diasReserva;
    }
 
    public void setDiasReserva(Integer diasReserva) {
        this.diasReserva = diasReserva;
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
 
    public String getEstado() {
        return estado;
    }
 
    public void setEstado(String estado) {
        this.estado = estado;
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
 
    public String getMarca() {
        return marca;
    }
 
    public void setMarca(String marca) {
        this.marca = marca;
    }
 
   
 
 
 
}