package br.com.alexcoimbra12.flat.ws.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alexcoimbra12.flat.ws.model.Hospede;

public class HospedeDAO {
	private static HospedeDAO instance;
	protected EntityManager entityManager;

	public static HospedeDAO getInstance() {
		if (instance == null) {
			instance = new HospedeDAO();
		}
		return instance;
	}

	public HospedeDAO() {
		this.entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
		if (this.entityManager == null) {
			this.entityManager = factory.createEntityManager();
		}
		return this.entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<Hospede> findAll() {
		return this.entityManager.createQuery("FROM " + Hospede.class.getName()).getResultList();
	}

	public void persist(Hospede hospede) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(hospede);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}
	}
}