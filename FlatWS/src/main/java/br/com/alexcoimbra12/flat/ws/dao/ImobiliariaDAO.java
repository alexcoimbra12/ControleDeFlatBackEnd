package br.com.alexcoimbra12.flat.ws.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

public class ImobiliariaDAO {
	
	private static ImobiliariaDAO instance;
	protected EntityManager entityManager;

	public static ImobiliariaDAO getInstance() {
		if (instance == null) {
			instance = new ImobiliariaDAO();
		}
		return instance;
	}

	public ImobiliariaDAO() {
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
	public List<Imobiliaria> findAll() {
		return this.entityManager.createQuery("FROM " + Imobiliaria.class.getName()).getResultList();
	}

	public void persist(Imobiliaria imobiliaria) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(imobiliaria);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}
	}
}
