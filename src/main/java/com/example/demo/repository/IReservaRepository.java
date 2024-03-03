package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;


import com.example.demo.repository.modelo.Reserva;



public interface IReservaRepository {

	public void guardar(Reserva r);

	public void actualizarReserva(Reserva r);

	public List<Reserva> buscarReserva(String placa);

	public Reserva buscarAutoReserva(String numeroReserva);

	
	public Reserva buscarPlaca(Integer id, String placa);

	public Reserva buscarReservaPlaca(String placa);

	public List<Reserva> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin);

	//public ClienteTo reporteCliente(Integer id, String cedula);

	//public VehiculoTo reporteVehiculo(Integer id, String placa);

	//public RetiroTo buscarReservas(String numero);
	
	public List<Reserva> seleccionarPorIdCiente(Integer id);

}
