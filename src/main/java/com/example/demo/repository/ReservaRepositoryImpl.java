package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Reserva;



@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	//@Transactional(value = TxType.MANDATORY)
	public void guardar(Reserva r) {
		this.entityManager.persist(r);
	}

	@Override
	//@Transactional(value = TxType.MANDATORY)
	public void actualizarReserva(Reserva r) {
		this.entityManager.merge(r);
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarReserva(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getResultList();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarAutoReserva(String numeroReserva) {
		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.numeroReserva=:datoNumero", Reserva.class);
		myQuery.setParameter("datoNumero", numeroReserva);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarPlaca(Integer id, String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE r.id=:datoId AND v.placa=:datoVehiculo",
				Reserva.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarReservaPlaca(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin",
				Reserva.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);

		return myQuery.getResultList();
	}
/*
	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public ClienteTo reporteCliente(Integer id, String cedula) {
		TypedQuery<ClienteTo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.ClienteTo(c.nombre, c.apellido)  FROM Reserva r INNER JOIN r.cliente c WHERE r.id=:datoId AND c.numeroCedula=:datoCedula",
				ClienteTo.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public VehiculoTo reporteVehiculo(Integer id, String placa) {
		TypedQuery<VehiculoTo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.VehiculoTo(v.placa, v.modelo, v.marca) FROM Reserva r INNER JOIN r.vehiculo v WHERE r.id=:datoId AND v.placa=:datoPlaca",
				VehiculoTo.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoPlaca", placa);
		return myQuery.getSingleResult();
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public RetiroTo buscarReservas(String numero) {
		TypedQuery<RetiroTo> myQuery = this.entityManager.createQuery(
				"SELECT  NEW  com.uce.edu.demo.repository.modelo.RetiroTo(v.placa, v.modelo, r.estado, r.fechaInicio, r.fechaFin, r.numero, c.numeroCedula) FROM Reserva r "
						+ "INNER JOIN r.vehiculo v INNER JOIN r.cliente c WHERE r.numero=:datoNumero",
				RetiroTo.class);
		myQuery.setParameter("datoNumero", numero);
		return myQuery.getSingleResult();
	}
*/
	@Override
	public List<Reserva> seleccionarPorIdCiente(Integer id) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.cliente.id =: id",Reserva.class);
		myQuery.setParameter("id", id);
		return myQuery.getResultList();
	}

}
