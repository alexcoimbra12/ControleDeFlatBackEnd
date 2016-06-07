package br.com.alexcoimbra12.flat.ws.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexcoimbra12.flat.ws.constants.WSConstants;
import br.com.alexcoimbra12.flat.ws.dao.impl.HospedeDAOImpl;
import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

@RestController
@RequestMapping(value = WSConstants.REQUEST_HOSPEDE)
public class HospedeWS {

	@Autowired
	private HospedeDAOImpl dao;
	
	private ResultMessage resultMessage = new ResultMessage();
	
	private static final Logger log = LogManager.getLogger(HospedeWS.class);

	@RequestMapping(value = WSConstants.GET_NAME, method = RequestMethod.GET, produces = WSConstants.PRODUCES_JSON)
	public List<Hospede> findByName(@PathVariable(value = WSConstants.VARIABLE_NAME) String nome) throws ListException {
		log.info("Procurando Lista de Hospedes");
		List<Hospede> hospedesList = new ArrayList<Hospede>();
		hospedesList = dao.findByName(nome);
		log.info("Retornando lista de Hospedes Encontrados");
		return hospedesList;
	}

	@RequestMapping(value = WSConstants.POST_SAVE, method = RequestMethod.POST, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage persistHospede(@RequestBody Hospede hospede) {
		log.info("Salvando novo Hospede " + hospede.toString());
		int result = dao.persist(hospede);

		if (result == 1) {
			resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
		} else {
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}
		log.info("A operação de salvar retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = WSConstants.PUT_MERGE, method = RequestMethod.PUT, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage editHospede(@RequestBody Hospede hospede) throws HospedeNullException {
		log.info("Editando Usuário");
		Hospede hospede2 = new Hospede();
		log.info("Verificando o id do hospede");
		if(hospede.getId() == 0) {
			log.error("Id inválido \n id informado é " + hospede.getId());
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}else{
			log.info("Recuperando hospede com o id " + hospede.getId());
			hospede2 = dao.getById(hospede.getId());
			hospede2.setNome(hospede.getNome());
			hospede2.setCpf(hospede.getCpf());
			hospede2.setTelefone(hospede.getTelefone());
			log.info("Editando o hospede com as novas informações");
			int result = dao.merge(hospede2);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de editar o Hospede retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = WSConstants.DELETE_ID, method = RequestMethod.DELETE, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage removeById(@PathVariable(value = WSConstants.VARIABLE_ID) int id) {
		log.info("Removendo hospede \n" + "Verificando o id do hospede");
		if (id == 0) {
			log.error("Id inválido \n id informado é " + id);
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		} else {
			log.info("Removendo Hospede com o Id " + id);
			int result = dao.removeById(id);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de deletar o Hospede retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

}