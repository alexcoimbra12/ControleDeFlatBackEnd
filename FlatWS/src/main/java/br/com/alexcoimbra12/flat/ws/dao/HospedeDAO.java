package br.com.alexcoimbra12.flat.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alexcoimbra12.flat.ws.exception.ListException;
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
	public List<Hospede> findAll() throws ListException {
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		hospedeList = this.entityManager.createQuery("FROM " + Hospede.class.getName()).getResultList();
		
		if(hospedeList == null){
			throw new ListException("Erro ao recuperar lista de Hospedes, lista é null");
		}else {
			return hospedeList;
		}
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
	
	@SuppressWarnings("unchecked")
	public List<Hospede> findByName(String nome) throws ListException{
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		if (nome == null || nome.equals("")) {
			return findAll();
		} else {
			hospedeList = entityManager.createQuery("SELECT c FROM Hospede c WHERE c.nome LIKE :nome").setParameter("nome", "%"+nome+"%").getResultList();
			
			if(hospedeList == null){
				throw new ListException("Erro ao recuperar lista de Hospedes, lista é null");
			}else {
				return hospedeList;
			}
		}
	}
	
	public Hospede getById (final int id) {
		return entityManager.find(Hospede.class, id);
	}
	
	public void merge(Hospede hospede) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(hospede);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remove (Hospede hospede) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(hospede);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public int removeById (final int id) {
		try {
			Hospede hospede = getById(id);
			if(hospede == null){
				return 0;
			}
			remove(hospede);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}	