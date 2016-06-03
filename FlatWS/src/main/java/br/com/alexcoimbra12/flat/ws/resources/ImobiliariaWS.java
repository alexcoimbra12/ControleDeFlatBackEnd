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
import br.com.alexcoimbra12.flat.ws.controller.ImobiliariaController;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

@RestController
@RequestMapping(value = "/imobiliaria")
public class ImobiliariaWS {
	
	private ResultMessage resultMessage = new ResultMessage();
	
	private static final Logger log = LogManager.getLogger(ImobiliariaWS.class);

	@RequestMapping(value = "/findImobiliaria", params = "nome", method = RequestMethod.GET, produces = "application/json")
	public List<Imobiliaria> findByName(@RequestParam(value = "nome") String nome) throws ListException {
		log.info("Procurando Lista de Imobiliarias");
		List<Imobiliaria> imobiliariaList = new ArrayList<Imobiliaria>();
		imobiliariaList = new ImobiliariaController().findByName(nome);
		log.info("Retornando lista de Imobiliarias Encontrados");
		return imobiliariaList;
	}

	@RequestMapping(value = "/saveImobiliaria", method = RequestMethod.POST, produces = "application/json")
	public ResultMessage persistImobiliaria(@RequestBody Imobiliaria imobiliaria) {
		log.info("Salvando novo Imobiliaria " + imobiliaria.toString());
		int result = new ImobiliariaController().persistImobiliaria(imobiliaria);

		if (result == 1) {
			resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
		} else {
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}
		log.info("A operação de salvar retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = "/editImobiliaria", method = RequestMethod.PUT, produces = "application/json")
	public ResultMessage editImobiliaria(@RequestBody Imobiliaria imobiliaria) throws ListException {
		log.info("Editando Usuário");
		Imobiliaria imobiliaria2 = new Imobiliaria();
		log.info("Verificando o id do Imobiliaria");
		if(imobiliaria.getId() == 0){
			log.error("Id inválido \n id informado é " + imobiliaria.getId());
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		} else {
			log.info("Recuperando Imobiliaria com o id " + imobiliaria.getId());
			imobiliaria2 = new ImobiliariaController().getById(imobiliaria.getId());
			imobiliaria2.setNome(imobiliaria.getNome());
			imobiliaria2.setEndereco(imobiliaria.getEndereco());
			imobiliaria2.setNomeCorretor(imobiliaria.getNomeCorretor());
			imobiliaria2.setNumCreci(imobiliaria.getNumCreci());
			imobiliaria2.setTelContato(imobiliaria.getTelContato());
			log.info("Editando o Imobiliaria com as novas informações");
			int result = new ImobiliariaController().editImobiliaria(imobiliaria2);
			
			if (result == 1) {
				resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
			} else {
				resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
			}
		}
		log.info("A operação de editar o Imobiliaria retornou " + resultMessage.getResultMessage());
		return resultMessage;
	}

	@RequestMapping(value = "/removeImobiliaria", params = "id", method = RequestMethod.DELETE, produces = "application/json")
	public ResultMessage removeById(@RequestParam(value = "id") Integer id) {
		log.info("Removendo Imobiliaria \n"
				+ "Verificando o id do Imobiliaria");
		if(id == 0){
			log.error("Id inválido \n id informado é " + id);
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}else {
			log.info("Removendo Imobiliaria com o Id " + id);
			int result = new ImobiliariaController().removeById(id);
			
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
