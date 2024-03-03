package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.modelo.Cliente;
import com.example.demo.service.to.ClienteTO;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository iClienteRepository;

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<ClienteTO> buscarPorApellido(String apellido) {
		List<Cliente> lista = this.iClienteRepository.buscarPorApellido(apellido);
		List<ClienteTO> listaFinal = new ArrayList<>();

		for (Cliente clie : lista) {
			listaFinal.add(this.convertir(clie));
		}
		return listaFinal;
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public void eliminar(Integer id) {
		this.iClienteRepository.eliminar(id);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public Cliente buscarPorId(Integer id) {
		return this.iClienteRepository.buscarPorId(id);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(ClienteTO cliente) {
		Cliente c = this.convertirTOaCliente(cliente);
		this.iClienteRepository.actualizar(c);

	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public boolean registro(ClienteTO cliente) {
		cliente.setRegistro("C");

		Cliente clie = this.convertirTOaCliente(cliente);
		System.out.println(clie.getNombre());
		return this.iClienteRepository.insertar(clie);

	}

	private Cliente convertirTOaCliente(ClienteTO cliente) {

		Cliente clie = new Cliente();

		clie.setId(cliente.getId());
		clie.setNombre(cliente.getNombre());
		clie.setApellido(cliente.getApellido());
		clie.setNumeroCedula(cliente.getNumeroCedula());
		clie.setFechaNacimiento(cliente.getFechaNacimiento());
		clie.setGenero(cliente.getGenero());
		clie.setRegistro(cliente.getRegistro());

		return clie;
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public boolean registroComoEmpleado(ClienteTO cliente) {
		cliente.setRegistro("E");

		Cliente clie = this.convertirTOaCliente(cliente);
		System.out.println(clie.getRegistro());
		return this.iClienteRepository.insertar(clie);

	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public ClienteTO buscarPorCedula(String cedula) {

		Cliente cliente = this.iClienteRepository.buscarCedula(cedula);

		return this.convertir(cliente);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<ClienteTO> buscarTodos() {
		List<Cliente> lista = this.iClienteRepository.buscarTodos();
		List<ClienteTO> listaFinal = new ArrayList<>();

		for (Cliente clie : lista) {
			listaFinal.add(this.convertir(clie));
		}

		return listaFinal;
	}

	private ClienteTO convertir(Cliente clie) {

		ClienteTO clieTO = new ClienteTO();

		clieTO.setId(clie.getId());
		clieTO.setNombre(clie.getNombre());
		clieTO.setApellido(clie.getApellido());
		clieTO.setNumeroCedula(clie.getNumeroCedula());
		clieTO.setFechaNacimiento(clie.getFechaNacimiento());
		clieTO.setGenero(clie.getGenero());
		clieTO.setRegistro(clie.getRegistro());

		return clieTO;

	}

	@Override
	public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento, String genero,
			String registro, Integer id) {
		this.iClienteRepository.actualizarParcial(nombre, apellido, fechaNacimiento, genero, registro, id);
	}

	public boolean existeClienteConCedula(String cedula) {
		// TODO Auto-generated method stub
		try {
			ClienteTO cliente = this.buscarPorCedula(cedula);
			return cliente != null;
		} catch (Exception e) {
			// Manejar la excepción según tus necesidades
			return false;
		}
	}

	public boolean validarCliente(ClienteTO cliente) {
		// Verificar que el objeto no sea nulo
		if (cliente == null) {
			return false;
		}

		// Verificar que el atributo cedula no sea nulo ni vacío
		if (cliente.getNumeroCedula() == null || cliente.getNumeroCedula().isEmpty()) {
			return false;
		}

		// Verificar que el atributo apellido no sea nulo ni vacío
		if (cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
			return false;
		}

		// Si pasa todas las validaciones, retorna true
		return true;
	}

}
