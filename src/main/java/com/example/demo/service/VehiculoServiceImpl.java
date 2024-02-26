package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepository;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;


import com.example.demo.service.to.VehiculoTO;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
		return this.iVehiculoRepository.vehiculosDisponibles(marca, modelo);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public Vehiculo buscarPorId(Integer id) {
		return this.iVehiculoRepository.buscarPorId(id);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public void insertar(Vehiculo vehiculo) {
		vehiculo.setEstado("Disponible");
		this.iVehiculoRepository.ingresarVehiculo(vehiculo);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(Vehiculo vehiculo) {
		this.iVehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public void eliminar(Integer id) {
		this.iVehiculoRepository.eliminar(id);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarTodos() {
		return this.iVehiculoRepository.buscarTodos();
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarPorMarca(String marca) {
		return this.iVehiculoRepository.buscarPorMarca(marca);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorPlaca(placa);
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorFechas(fechaInicio, fechaFin);
	}
	

	
	@Override
	public List<VehiculoTO> buscarPorMarcayModelo(String marca, String Modelo) {
		List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarcayModelo(marca, Modelo);
		List<VehiculoTO> listaFinal = new ArrayList<>();
		
		for(Vehiculo vehi: lista) {
			listaFinal.add(this.convertir(vehi));
		}
		return listaFinal;
	}
	
	private VehiculoTO convertir(Vehiculo v) {
		VehiculoTO vehiTO = new VehiculoTO();
		
		vehiTO.setId(v.getId());
		vehiTO.setPlaca(v.getPlaca());
		vehiTO.setModelo(v.getModelo());
		vehiTO.setMarca(v.getMarca());
		vehiTO.setAnioFabricacion(v.getAnioFabricacion());
		vehiTO.setEstado(v.getEstado());
		vehiTO.setPaisFabricacion(v.getPaisFabricacion());
		vehiTO.setCilindraje(v.getCilindraje());
		vehiTO.setAvaluo(v.getAvaluo());
		vehiTO.setRenta(v.getRenta());
		vehiTO.setCombustible(v.getCombustible());
		
		return vehiTO;
	}
}
