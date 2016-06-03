package br.com.alexcoimbra12.flat.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

public class HospedeDAO {
	
	private static HospedeDAO instance;
	protected EntityManager entityManager;
	
	private Logger log = LogManager.getLogger(HospedeDAO.class);
	
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
		log.info("Recuperando todos os Hospedes");
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		hospedeList = this.entityManager.createQuery("FROM " + Hospede.class.getName()).getResultList();
		
		if(hospedeList == null){
			log.error("Erro ao recuperar lista de Hospedes, lista é " + hospedeList);
			throw new ListException("Erro ao recuperar lista de Hospedes, lista é null");
		}else {
			return hospedeList;
		}
	}

	public int persist(Hospede hospede) {
		try {
			log.info("Realizando a operação de salvar o hospede");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(hospede);
			this.entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao salvar Hospede no banco " + e);
			this.entityManager.getTransaction().rollback();
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Hospede> findByName(String nome) throws ListException{
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		log.info("Verificando se foi informando nome para realizar busca por nome");
		if (nome == null || nome.equals("")) {
			log.info("Nome está vazio Chamando Metodo para retornar todos os hospedes");
			return findAll();
		} else {
			hospedeList = entityManager.createQuery("SELECT c FROM Hospede c WHERE c.nome LIKE :nome").setParameter("nome", "%"+nome+"%").getResultList();
			if(hospedeList == null || hospedeList.isEmpty()){
				log.error("Erro ao recuperar lista de Hospedes com o nome: " + nome);
				throw new ListException("Erro ao recuperar lista de Hospedes com o nome: " + nome);
			}else {
				log.debug("Retornado Hospedes com o nome " + nome + " " + hospedeList.toString());
				return hospedeList;
			}
		}
	}
	
	public Hospede getById (final int id) throws ListException {
		log.info("Recuperando hospede com id " + id);
		Hospede hospede = new Hospede();
		hospede = entityManager.find(Hospede.class, id);
		if (hospede == null) {
			log.error("Hospede com o ID: " + id + " não encontrado, id não existe no banco");
			throw new ListException("Hospede com o ID: " + id + " não encontrado, id não existe no banco");
		}
		return hospede;
	}
	
	public int merge(Hospede hospede) {
		try {
			log.info("Realizando a operação de merge do hospede");
			entityManager.getTransaction().begin();
			entityManager.merge(hospede);
			entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao realizar o merge Hospede no banco " + e);
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	
	public int removeById (final int id) {
		try {
			log.info("Realizando a operação para deletar o hospede");
			log.info("Recuperando Hospede com o id " + id + " para deletar");
			Hospede hospede = getById(id);
			entityManager.getTransaction().begin();
			entityManager.remove(hospede);
			entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao deletar Hospede no banco " + e);
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
}	