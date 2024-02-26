package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;



import com.example.demo.repository.modelo.Cobro;

@Repository
@Transactional
public class CobroRepositoryImpl implements ICobroRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	//@Transactional(value = TxType.MANDATORY)
	public void insertarPago(Cobro c) {
		this.entityManager.persist(c);
	}

}
