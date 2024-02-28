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
import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.DTO.VehiculoDTO;
import com.example.demo.service.to.ClienteTO;
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
	public void insertar(VehiculoTO vehiculo) {
		vehiculo.setEstado("Disponible");
		Vehiculo vehi = this.convertirTOaVehiculo(vehiculo);
		this.iVehiculoRepository.ingresarVehiculo(vehi);
	}
	
	private Vehiculo convertirTOaVehiculo(VehiculoTO vehiculo) {
		
		Vehiculo vehi = new Vehiculo();
		
		vehi.setId(vehiculo.getId());
		vehi.setPlaca(vehiculo.getPlaca());
		vehi.setModelo(vehiculo.getModelo());
		vehi.setMarca(vehiculo.getMarca());
		vehi.setAnioFabricacion(vehiculo.getAnioFabricacion());
		vehi.setEstado(vehiculo.getEstado());
		vehi.setPaisFabricacion(vehiculo.getPaisFabricacion());
		vehi.setCilindraje(vehiculo.getCilindraje());
		vehi.setCombustible(vehiculo.getCombustible());
		vehi.setAvaluo(vehiculo.getAvaluo());
		vehi.setRenta(vehiculo.getRenta());
		
		return vehi;
	}

	@Override
	//@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(VehiculoTO vehiculo) {
		Vehiculo vehi = this.convertirToVehiculo(vehiculo);
		this.iVehiculoRepository.actualizarEstado(vehi);;
	}

	private Vehiculo convertirToVehiculo(VehiculoTO v) {
		
		Vehiculo vehi = new Vehiculo();
		
		
		vehi.setId(v.getId());
		vehi.setPlaca(v.getPlaca());
		vehi.setModelo(v.getModelo());
		vehi.setMarca(v.getMarca());
		vehi.setAnioFabricacion(v.getAnioFabricacion());
		vehi.setEstado(v.getEstado());
		vehi.setPaisFabricacion(v.getPaisFabricacion());
		vehi.setCilindraje(v.getCilindraje());
		vehi.setAvaluo(v.getAvaluo());
		vehi.setRenta(v.getRenta());
		vehi.setCombustible(v.getCombustible());
		
		return vehi;
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
	public List<VehiculoTO> buscarPorMarca(String marca) {
		
		List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarca(marca);
		List<VehiculoTO> listaFinal = new ArrayList<>();
		
		for(Vehiculo vehi : lista) {
			listaFinal.add(this.convertir(vehi));
		}
		return listaFinal;
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

	@Override
	public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarVehiculosPorMarcayModelo(marca, modelo);
	}
}
