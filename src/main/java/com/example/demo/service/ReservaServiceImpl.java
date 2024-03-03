package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.IReservaRepository;
import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Cliente;

import com.example.demo.repository.modelo.Reserva;

import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.DTO.ReservaDTO;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private IReservaRepository reservaRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public Reserva reservarVehiculo(String placa, String cedula, LocalDate inicio, LocalDate fin) {
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(placa);
		Cliente cliente = this.clienteRepository.buscarCedula(cedula);

		LocalDate fInicio = inicio;
		LocalDate fFin = fin;

		int dias = Period.between(fInicio, fFin).getDays() + 1;

		Reserva reserva = new Reserva();

		int numero = (int) (Math.random() * 100 + 1);
		String cadena = cedula.substring(0, 5);
		String codigoReserva = "R" + numero + "-" + cadena;

		reserva.setNumeroReserva(cedula);
		reserva.setDiasReserva(dias);
		reserva.setFechaInicio(inicio);
		reserva.setFechaFin(fin);
		reserva.setEstado("Reservado");
		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);
		reserva.setNumero(codigoReserva);

		this.reservaRepository.guardar(reserva);
		System.out.println("El vehiculo ha sido reservado");
		return reserva;
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public boolean buscarvehiculoDisponible(String placa, LocalDate inicio, LocalDate fin) {
		List<Reserva> reservas = this.reservaRepository.buscarReserva(placa);
		LocalDate fInicio = inicio;
		LocalDate fFin = fin;

		if (reservas.size() == 0) {
			return true;
		} else {

			for (Reserva dato : reservas) {
				LocalDate fechaInicio = dato.getFechaInicio();
				LocalDate fechaFin = dato.getFechaFin();

				if (fechaInicio.compareTo(fInicio) > 0 && fechaInicio.compareTo(fFin) > 0
						|| fechaFin.compareTo(fInicio) < 0 && fechaFin.compareTo(fFin) < 0) {
					// System.out.println("Disponible, excepto para estas fechas
					// :"+dato.getFechaInicio()+" - "+dato.getFechaFin());
				} else {
					System.out.println(
							"El auto ya esta reservado :" + dato.getFechaInicio() + " - " + dato.getFechaFin());
					return false;
				}
			}
			return true;
		}
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public void retiro(String numero) {
		Reserva reserva = this.reservaRepository.buscarAutoReserva(numero);
		reserva.setEstado("Retirado");
		this.reservaRepository.actualizarReserva(reserva);

		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		vehiculo.setEstado("No Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public ReservaDTO buscarAutoReserva(String numero) {
		
		Reserva reserva=this.reservaRepository.buscarAutoReserva(numero);
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		
		ReservaDTO reservadto= new ReservaDTO();
		reservadto.setPlaca(vehiculo.getPlaca());
		reservadto.setEstado(vehiculo.getEstado());
		reservadto.setModelo(vehiculo.getModelo());
		reservadto.setFecha(reserva.getFechaFin());
		reservadto.setReservadoPor(reserva.getNumero());
		
		return  reservadto;
			
	}

/*	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<ReservaTo> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {

		List<ReservaTo> listaReporte = new ArrayList<>();
		List<Reserva> listaReservas = this.reservaRepository.buscarPorFechas(fechaInicio, fechaFin);

		for (Reserva lista : listaReservas) {
			ClienteTo cliente = this.reservaRepository.reporteCliente(lista.getId(),
					lista.getCliente().getNumeroCedula());
			VehiculoTO vehiculo = this.reservaRepository.reporteVehiculo(lista.getId(), lista.getVehiculo().getPlaca());

			ReservaTo reserva = new ReservaTo();

			reserva.setNombre(cliente.getNombre());
			reserva.setApellido(cliente.getApellido());
			reserva.setPlaca(vehiculo.getPlaca());
			reserva.setModelo(vehiculo.getModelo());
			reserva.setMarca(vehiculo.getMarca());

			reserva.setNumero(lista.getNumero());
			reserva.setDiasReserva(lista.getDiasReserva());
			reserva.setFechaInicio(lista.getFechaInicio());
			reserva.setFechaFin(lista.getFechaFin());
			reserva.setEstado(lista.getEstado());

			listaReporte.add(reserva);
		}

		return listaReporte;
	}
*/
	//BUSCAR RESERVAS POR ID DEL CLIENTE
	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<ReservaTO> buscarReservasPorIdCliente(Integer id) {
		// TODO Auto-generated method stub
		List<Reserva> lista = this.reservaRepository.seleccionarPorIdCiente(id);
		
		List<ReservaTO> listaFinal = new ArrayList<>();
		
		for(Reserva res: lista) {
			listaFinal.add(this.convertir(res));
		}
		
		return listaFinal;
	}

	private ReservaTO convertir(Reserva reserva) {
		ReservaTO reservaTO = new ReservaTO();
		
		reservaTO.setId(reserva.getId());
		reservaTO.setDiasReserva(reserva.getDiasReserva());
		reservaTO.setEstado(reserva.getEstado());
		reservaTO.setFechaInicio(reserva.getFechaInicio());
		reservaTO.setFechaFin(reserva.getFechaFin());
		reservaTO.setNumero(reserva.getNumero());
		reservaTO.setNumeroReserva(reserva.getNumeroReserva());
		
		return reservaTO;
		
		
	}

	@Override
	public List<ReporteTO> Reporte(LocalDate fechaInicio, LocalDate fechaFin) {
		List<Reserva> lista = this.reservaRepository.buscarPorFechas(fechaInicio, fechaFin);
		List<ReporteTO> listaFinal = new ArrayList<>();
		ReporteTO reporteTO = new ReporteTO();


		for (Reserva res : lista) {
			
			Cliente cliente = this.clienteRepository.buscarPorId(res.getCliente().getId());
			Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(res.getVehiculo().getPlaca());
			reporteTO.setNumeroReserva(res.getNumero());
			reporteTO.setDiasReserva(res.getDiasReserva());
			reporteTO.setFechaInicio(res.getFechaInicio());
			reporteTO.setFechaFin(res.getFechaFin());
			reporteTO.setEstado(res.getEstado());
			reporteTO.setApellido(cliente.getApellido());
			reporteTO.setNumeroCedula(cliente.getNumeroCedula());
			reporteTO.setPlaca(vehiculo.getPlaca());
			reporteTO.setModelo(vehiculo.getModelo());
			reporteTO.setMarca(vehiculo.getMarca());
			listaFinal.add(reporteTO);
		}

		return listaFinal;
	}

	@Override
	public void retirarVehiculo(String numeroReserva) {
		// TODO Auto-generated method stub
	//Funcionalidad que permitirá retirar un vehículo previamente reservado; el sistema permitirá ingresar un número de reserva 
	//y cambiará el estado de la reserva a "ejecutada" y el estado del vehículo a "No Disponible"
		Reserva reserva = this.reservaRepository.buscarAutoReserva(numeroReserva);
		reserva.setEstado("Ejecutada");
		this.reservaRepository.actualizarReserva(reserva);
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		vehiculo.setEstado("No Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);	
	}
			
		
	}

	

	
