package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.Cliente;

import com.example.demo.service.to.ClienteTO;

public interface IClienteService {

	public void registro(ClienteTO cliente);

	public void registroComoEmpleado(Cliente cliente);

	public List<ClienteTO> buscarPorApellido(String apellido);

	public void eliminar(Integer id);

	public Cliente buscarPorId(Integer id);

	public void actualizar(Cliente cliente);

	public Cliente buscarPorCedula(String cedula);

	public List<ClienteTO> buscarTodos();

	public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento,String genero,String registro,Integer id);
	
}
