package br.com.alexcoimbra12.flat.ws.resources;

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
import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ImobiliariaNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

@RestController
@RequestMapping(value = WSConstants.REQUEST_IMOBILIARIA)
public class ImobiliariaWS {
	
	@Autowired
	private ImobiliariaDAO dao;
	
	private ResultMessage resultMessage = new ResultMessage();
	
	private static final Logger log = LogManager.getLogger(ImobiliariaWS.class);

	@RequestMapping(value = WSConstants.GET_NAME, method = RequestMethod.GET, produces = WSConstants.PRODUCES_JSON)
	public List<Imobiliaria> findByName(@PathVariable(value = WSConstants.VARIABLE_NAME) String nome) throws ListException {
		
		log.info("Procurando Lista de Imobiliarias");
		List<Imobiliaria> imobiliariaList = dao.findByName(nome);
		log.info("Retornando lista de Imobiliarias Encontrados");
		return imobiliariaList;
	}
	
	@RequestMapping(value = WSConstants.GET_ALL, method = RequestMethod.GET, produces = WSConstants.PRODUCES_JSON)
	public List<Imobiliaria> findAll() throws ListException{
		return dao.findAll();
	}
	

	@RequestMapping(value = WSConstants.POST_SAVE, method = RequestMethod.POST, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage persistImobiliaria(@RequestBody Imobiliaria imobiliaria) {
		
		log.info("Salvando nova Imobiliaria " + imobiliaria.toString());
		int result = dao.persist(imobiliaria);

		if (result == 1) {
			resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
		} else {
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}
		log.info("A operação de salvar retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = WSConstants.PUT_MERGE, method = RequestMethod.PUT, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage editImobiliaria(@RequestBody Imobiliaria imobiliaria) throws ImobiliariaNullException {
		
		log.info("Editando Usuário com id " + imobiliaria.getId());
		Imobiliaria imobiliaria2 = new Imobiliaria();

		if(imobiliaria.getId() == 0){
			log.error("Id inválido \n id informado é " + imobiliaria.getId());
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		} else {
			imobiliaria2 = dao.getById(imobiliaria.getId());
			imobiliaria2.setNome(imobiliaria.getNome());
			imobiliaria2.setEndereco(imobiliaria.getEndereco());
			imobiliaria2.setNomeCorretor(imobiliaria.getNomeCorretor());
			imobiliaria2.setNumCreci(imobiliaria.getNumCreci());
			imobiliaria2.setTelContato(imobiliaria.getTelContato());
			log.info("Editando o Imobiliaria com as novas informações");
			int result = dao.merge(imobiliaria2);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de editar o Imobiliaria retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = WSConstants.DELETE_ID, method = RequestMethod.DELETE, produces = WSConstants.PRODUCES_JSON)
	public ResultMessage removeById(@PathVariable(value = WSConstants.VARIABLE_ID) Integer id) {
		
		log.info("Removendo Imobiliaria com id " + id);
		if(id == 0){
			log.error("Id inválido \n id informado é " + id);
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}else {
			log.info("Removendo Imobiliaria com o Id " + id);
			int result = dao.removeById(id);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de deletar o Imobiliaria retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}
	
}
