package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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
