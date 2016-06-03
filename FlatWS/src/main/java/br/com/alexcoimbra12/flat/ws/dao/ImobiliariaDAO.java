package br.com.alexcoimbra12.flat.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

public class ImobiliariaDAO {

	private static ImobiliariaDAO instance;
	protected EntityManager entityManager;
	
	private Logger log = LogManager.getLogger(ImobiliariaDAO.class);

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
	public List<Imobiliaria> findAll() throws ListException {
		log.info("Recuperando todos os Imobiliarias");
		List<Imobiliaria> imobiliariaList = new ArrayList<Imobiliaria>();
		imobiliariaList = this.entityManager.createQuery("FROM " + Imobiliaria.class.getName()).getResultList();

		if (imobiliariaList == null) {
			log.error("Erro ao recuperar lista de Imobiliarias, lista é " + imobiliariaList);
			throw new ListException("Erro ao recuperar lista de Imobiliarias, lista é null");
		} else {
			return imobiliariaList;
		}
	}

	public int persist(Imobiliaria imobiliaria) {
		try {
			log.info("Realizando a operação de salvar o Imobiliaria");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(imobiliaria);
			this.entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao salvar Imobiliaria no banco " + e);
			this.entityManager.getTransaction().rollback();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Imobiliaria> findByName(String nome) throws ListException {
		List<Imobiliaria> imobiliariaList = new ArrayList<Imobiliaria>();
		log.info("Verificando se foi informando nome para realizar busca por nome");
		if (nome == null || nome.equals("")) {
			log.info("Nome está vazio Chamando Metodo para retornar todos os Imobiliarias");
			return findAll();
		} else {
			imobiliariaList = entityManager.createQuery("SELECT c FROM Imobiliaria c WHERE c.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();

			if (imobiliariaList == null || imobiliariaList.isEmpty()) {
				log.error("Erro ao recuperar lista de Imobiliarias com o nome: " + nome);
				throw new ListException("Erro ao recuperar lista de Imobiliarias com o nome: " + nome);
			} else {
				log.debug("Retornado Imobiliarias com o nome " + nome + " " + imobiliariaList.toString());
				return imobiliariaList;
			}
		}
	}

	public Imobiliaria getById(final int id) throws ListException {
		log.info("Recuperando Imobiliaria com id " + id);
		Imobiliaria imobiliaria = new Imobiliaria();
		imobiliaria = entityManager.find(Imobiliaria.class, id);
		if (imobiliaria == null) {
			log.error("Imobiliaria com o ID: " + id + " não encontrado, id não existe no banco");
			throw new ListException("Imobiliaria com o ID: " + id + " não encontrada, id não existe no banco");
		}
		return imobiliaria;
	}

	public int merge(Imobiliaria imobiliaria) {
		try {
			log.info("Realizando a operação de merge do Imobiliaria");
			entityManager.getTransaction().begin();
			entityManager.merge(imobiliaria);
			entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao realizar o merge Imobiliaria no banco " + e);
			entityManager.getTransaction().rollback();
			return 0;
		}
	}

	public int removeById(final int id) {
		try {
			log.info("Realizando a operação para deletar o Imobiliaria");
			log.info("Recuperando Imobiliaria com o id " + id + " para deletar");
			Imobiliaria imobiliaria = getById(id);
			entityManager.getTransaction().begin();
			entityManager.remove(imobiliaria);
			entityManager.getTransaction().commit();
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao deletar Imobiliaria no banco " + e);
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
}