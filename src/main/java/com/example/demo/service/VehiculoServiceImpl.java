package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.DTO.VehiculoDTO;
import com.example.demo.service.to.VehiculoTO;
import com.example.demo.service.to.VehiculoTO2;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
		return this.iVehiculoRepository.vehiculosDisponibles(marca, modelo);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public Vehiculo buscarPorId(Integer id) {
		return this.iVehiculoRepository.buscarPorId(id);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public boolean insertar(VehiculoTO vehiculo) {
		vehiculo.setEstado("Disponible");
		Vehiculo vehi = this.convertirTOaVehiculo(vehiculo);
		return this.iVehiculoRepository.ingresarVehiculo(vehi);
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
	// @Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(VehiculoTO vehiculo) {

		Vehiculo vehi = this.convertirToVehiculo(vehiculo);
		this.iVehiculoRepository.actualizarEstado(vehi);
		;
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
	// @Transactional(value = TxType.REQUIRES_NEW)
	public void eliminar(Integer id) {
		this.iVehiculoRepository.eliminar(id);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarTodos() {
		return this.iVehiculoRepository.buscarTodos();
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<VehiculoTO> buscarPorMarca(String marca) {

		List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarca(marca);
		List<VehiculoTO> listaFinal = new ArrayList<>();

		for (Vehiculo vehi : lista) {
			listaFinal.add(this.convertir(vehi));
		}
		return listaFinal;

	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public VehiculoTO buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.iVehiculoRepository.buscarPorPlaca(placa);
		return convertir(vehiculo);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorFechas(fechaInicio, fechaFin);
	}

	@Override
	public List<VehiculoTO> buscarPorMarcayModelo(String marca, String Modelo) {
		List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarcayModelo(marca, Modelo);
		List<VehiculoTO> listaFinal = new ArrayList<>();

		for (Vehiculo vehi : lista) {
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

	private VehiculoTO2 convertir22(Vehiculo v) {
		VehiculoTO2 vehiTO2 = new VehiculoTO2();

		vehiTO2.setId(v.getId());
		vehiTO2.setPlaca(v.getPlaca());
		vehiTO2.setModelo(v.getModelo());
		vehiTO2.setMarca(v.getMarca());
		vehiTO2.setAnioFabricacion(v.getAnioFabricacion());
		vehiTO2.setEstado(v.getEstado());
		vehiTO2.setPaisFabricacion(v.getPaisFabricacion());
		vehiTO2.setCilindraje(v.getCilindraje());
		vehiTO2.setAvaluo(v.getAvaluo());
		vehiTO2.setRenta(v.getRenta());
		vehiTO2.setCombustible(v.getCombustible());
		vehiTO2.setReservas(v.getReservas());

		return vehiTO2;
	}

	@Override
	public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarVehiculosPorMarcayModelo(marca, modelo);
	}

	@Override
	public List<VehiculoTO2> buscarVehiculosPorMarcayModelo22(String marca, String modelo) {
		List<Vehiculo> lista = this.iVehiculoRepository.buscarVehiculosPorMarcayModelo22(marca, modelo);
		List<VehiculoTO2> listaFinal = new ArrayList<>();

		for (Vehiculo vehi : lista) {
			listaFinal.add(this.convertir22(vehi));
		}
		return listaFinal;
	}

	@Override
	public boolean existeVehiculoConPlaca(String placa) {
		// TODO Auto-generated method stub
		try {
			VehiculoTO vehiculo = this.buscarPorPlaca(placa);
			return vehiculo != null;
		} catch (Exception e) {
			// Manejar la excepción según tus necesidades
			return false;
		}
	}

	public boolean validarVehiculo(VehiculoTO vehiculo) {
		// Verificar que el objeto no sea nulo

		if ((vehiculo.getPlaca() == null || vehiculo.getMarca() == null)) {
			return false;
		}

		return true;
	}
}
