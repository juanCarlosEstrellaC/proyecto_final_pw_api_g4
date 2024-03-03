package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarCedula(String cedula) {
		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.numeroCedula= :datoCedula", Cliente.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.MANDATORY)
	public boolean insertar(Cliente cliente) {
	    try {
	        this.entityManager.persist(cliente); 
	        return true; 
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	return false; 
	    }
	}
	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarPorApellido(String apellido) {
		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.apellido = :datoApellido", Cliente.class);
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.getResultList();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorId(Integer id) {
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
    //@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	//@Transactional(value = TxType.MANDATORY)
	public void actualizar(Cliente cliente) {
		//this.entityManager.merge(cliente);
	
	Query query = this.entityManager.createQuery(
			"UPDATE Cliente c SET c.nombre= :valor1, c.apellido= : valor2, c.numeroCedula= :valor3, c.fechaNacimiento= : valor4, c.genero= :valor5 WHERE c.id= :valor6");
	
	 query.setParameter("valor1", cliente.getNombre()); 
	 query.setParameter("valor2", cliente.getApellido());
	 query.setParameter("valor3", cliente.getNumeroCedula()); 
	 query.setParameter("valor4", cliente.getFechaNacimiento()); 
	 query.setParameter("valor5", cliente.getGenero());
	 query.setParameter("valor6", cliente.getId());
	 query.executeUpdate();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarTodos() {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento, String genero,
			String registro, Integer id) {
		Query query=this.entityManager
				.createQuery("UPDATE Cliente c SET c.nombre =:valor1, c.apellido =:valor2, c.fechaNacimiento =: valor3, c.genero =: valor4, c.registro=:valor5 WHERE c.id =: valor6");
		
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", fechaNacimiento);
		query.setParameter("valor4", genero);
		query.setParameter("valor5", "C");
		query.setParameter("valor6", id);
		
		query.executeUpdate();
		
	}
}
