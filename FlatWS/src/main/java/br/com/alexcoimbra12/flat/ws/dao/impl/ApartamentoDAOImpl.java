package br.com.alexcoimbra12.flat.ws.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alexcoimbra12.flat.ws.dao.ApartamentoDAO;
import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Apartamento;

@Repository
public class ApartamentoDAOImpl implements ApartamentoDAO {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Apartamento> findAll() throws ListException {
		return entityManager.createQuery("FROM " + Apartamento.class.getName()).getResultList();
	}

	@Override
	@Transactional
	public Apartamento getById(int id) throws HospedeNullException {
		return entityManager.find(Apartamento.class, id);
	}

}
