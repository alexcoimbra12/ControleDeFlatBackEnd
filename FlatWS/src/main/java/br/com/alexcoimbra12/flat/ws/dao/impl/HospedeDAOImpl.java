package br.com.alexcoimbra12.flat.ws.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

@Repository
public class HospedeDAOImpl implements HospedeDAO{
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	private Logger log = LogManager.getLogger(HospedeDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Hospede> findAll() throws ListException {
		log.info("Recuperando todos os Hospedes");
		List<Hospede> hospedeList = entityManager.createQuery("FROM " + Hospede.class.getName()).getResultList();
		
		if(hospedeList == null){
			log.error("Erro ao recuperar lista de Hospedes, lista � " + hospedeList);
			throw new ListException("Erro ao recuperar lista de Hospedes, lista � null");
		}else {
			return hospedeList;
		}
	}
	
	@Override
	@Transactional
	public int persist(Hospede hospede) {
		
		try {
			log.info("Realizando a opera��o de salvar o hospede");
			entityManager.persist(hospede);
			log.info("Opera��o realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao salvar Hospede no banco " + e);
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Hospede> findByName(String nome) throws ListException{
		List<Hospede> hospedeList = entityManager.createQuery("SELECT c FROM Hospede c WHERE c.nome LIKE :nome").setParameter("nome", "%"+nome+"%").getResultList();
		
		if(hospedeList == null){
				log.error("Erro ao recuperar lista de Hospedes com o nome: " + nome);
				throw new ListException("Erro ao recuperar lista de Hospedes com o nome: " + nome);
			}else {
				log.debug("Retornado Hospedes com o nome " + nome + " " + hospedeList.toString());
				return hospedeList;
			}
	}

	@Override
	@Transactional
	public Hospede getById (final int id) throws HospedeNullException {
		
		log.info("Recuperando hospede com id " + id);
		Hospede hospede = entityManager.find(Hospede.class, id);
		if (hospede == null) {
			log.error("Hospede com o ID: " + id + " n�o encontrado, id n�o existe no banco");
			throw new HospedeNullException("Hospede com o ID: " + id + " n�o encontrado, id n�o existe no banco");
		}
		return hospede;
	}
	
	@Override
	@Transactional
	public int merge(Hospede hospede) {
		
		try {
			log.info("Realizando a opera��o de merge do hospede");
			entityManager.merge(hospede);
			log.info("Opera��o realizada");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao realizar o merge Hospede no banco " + e);
			return 0;
		}
	}

	@Override
	@Transactional
	public int removeById (final int id) {
		
		try {
			log.info("Realizando a opera��o para deletar o hospede");
			entityManager.joinTransaction();
			Hospede hospede = getById(id);
			entityManager.remove(hospede);
			log.info("Opera��o realizada, Hospede com id " + id + " removido");
			return 1;
		} catch (Exception e) {
			log.error("Erro ao deletar Hospede no banco " + e);
			return 0;
		}
	}
}	