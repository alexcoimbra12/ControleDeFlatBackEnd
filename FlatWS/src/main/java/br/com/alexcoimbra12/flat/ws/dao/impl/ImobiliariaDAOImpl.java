package br.com.alexcoimbra12.flat.ws.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ImobiliariaNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

@Repository
public class ImobiliariaDAOImpl implements ImobiliariaDAO{
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	private Logger log = LogManager.getLogger(ImobiliariaDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Imobiliaria> findAll() throws ListException {
		log.info("Recuperando todas as Imobiliarias");
		List<Imobiliaria> imobiliariaList = entityManager.createQuery("FROM " + Imobiliaria.class.getName()).getResultList();

		if (imobiliariaList == null) {
			log.error("Erro ao recuperar lista de Imobiliarias, lista é " + imobiliariaList);
			throw new ListException("Erro ao recuperar lista de Imobiliarias, lista é null");
		} else {
			return imobiliariaList;
		}
	}
	@Override
	@Transactional
	public int persist(Imobiliaria imobiliaria) {
		try {
			log.info("Realizando a operação de salvar o Imobiliaria");
			entityManager.persist(imobiliaria);
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao salvar Imobiliaria no banco " + e);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Imobiliaria> findByName(String nome) throws ListException {
		
		List<Imobiliaria> imobiliariaList = entityManager.createQuery("SELECT c FROM Imobiliaria c WHERE c.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();

			if (imobiliariaList == null) {
				log.error("Erro ao recuperar lista de Imobiliarias com o nome: " + nome);
				throw new ListException("Erro ao recuperar lista de Imobiliarias com o nome: " + nome);
			} else {
				log.debug("Retornado Imobiliarias com o nome " + nome + " " + imobiliariaList.toString());
				return imobiliariaList;
			}
	}

	@Override
	public Imobiliaria getById(final int id) throws ImobiliariaNullException {
		
		log.info("Recuperando Imobiliaria com id " + id);
		Imobiliaria imobiliaria = entityManager.find(Imobiliaria.class, id);
		
		if (imobiliaria == null) {
			log.error("Imobiliaria com o ID: " + id + " não encontrado, id não existe no banco");
			throw new ImobiliariaNullException("Imobiliaria com o ID: " + id + " não encontrada, id não existe no banco");
		}
		return imobiliaria;
	}
	@Override
	@Transactional
	public int merge(Imobiliaria imobiliaria) {
		
		try {
			log.info("Realizando a operação de merge do Imobiliaria");
			entityManager.merge(imobiliaria);
			log.info("Operação realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao realizar o merge Imobiliaria no banco " + e);
			return 0;
		}
	}
	@Override
	@Transactional
	public int removeById(final int id) {
	
		try {
			log.info("Realizando a operação para deletar o Imobiliaria");
			Imobiliaria imobiliaria = getById(id);
			entityManager.remove(imobiliaria);
			log.info("Operação realizada, Imobiliaria com id " + id + " removida");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao deletar Imobiliaria no banco " + e);
			return 0;
		}
	}
}