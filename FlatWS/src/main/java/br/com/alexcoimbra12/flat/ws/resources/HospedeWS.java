package br.com.alexcoimbra12.flat.ws.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexcoimbra12.flat.ws.constants.WSConstants;
import br.com.alexcoimbra12.flat.ws.controller.HospedeController;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

@RestController
@RequestMapping(value = "/hospede")
public class HospedeWS {

	private ResultMessage resultMessage = new ResultMessage();
	
	private static final Logger log = LogManager.getLogger(HospedeWS.class);

	@RequestMapping(value = "/findHospede", params = "nome", method = RequestMethod.GET, produces = "application/json")
	public List<Hospede> findByName(@RequestParam(value = "nome")
	String nome) throws ListException {
		log.info("Procurando Lista de Hospedes");
		List<Hospede> hospedesList = new ArrayList<Hospede>();
		hospedesList = new HospedeController().findByName(nome);
		log.info("Retornando lista de Hospedes Encontrados");
		return hospedesList;
	}

	@RequestMapping(value = "/saveHospede", method = RequestMethod.POST, produces = "application/json")
	public ResultMessage persistHospede(@RequestBody Hospede hospede) {
		log.info("Salvando novo Hospede " + hospede.toString());
		int result = new HospedeController().persistHospede(hospede);

		if (result == 1) {
			resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
		} else {
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}
		log.info("A operação de salvar retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = "/editHospede", method = RequestMethod.PUT, produces = "application/json")
	public ResultMessage editHospede(@RequestBody Hospede hospede) throws ListException {
		log.info("Editando Usuário");
		Hospede hospede2 = new Hospede();
		log.info("Verificando o id do hospede");
		if(hospede.getId() == 0) {
			log.error("Id inválido \n id informado é " + hospede.getId());
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}else{
			log.info("Recuperando hospede com o id " + hospede.getId());
			hospede2 = new HospedeController().getById(hospede.getId());
			hospede2.setNome(hospede.getNome());
			hospede2.setCpf(hospede.getCpf());
			hospede2.setTelefone(hospede.getTelefone());
			log.info("Editando o hospede com as novas informações");
			int result = new HospedeController().editHospede(hospede2);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de editar o Hospede retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = "/removeHospede", params = "id", method = RequestMethod.DELETE, produces = "application/json")
	public ResultMessage removeById(@RequestParam(value = "id") int id) {
		log.info("Removendo hospede \n"
				+ "Verificando o id do hospede");
		if (id == 0) {
			log.error("Id inválido \n id informado é " + id);
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		} else {
			log.info("Removendo Hospede com o Id " + id);
			int result = new HospedeController().removeById(id);
			
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