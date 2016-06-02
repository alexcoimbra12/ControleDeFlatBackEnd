package br.com.alexcoimbra12.flat.ws.resources;

import java.util.List;

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

	@RequestMapping(value = "/getHospedes", method = RequestMethod.GET, produces = "application/json")
	public List<Hospede> findAll() throws ListException {
		return new HospedeController().findAll();
	}

	@RequestMapping(value = "/saveHospede", method = RequestMethod.POST)
	public void persistHospede(Hospede hospede) {
		new HospedeController().persistHospede(hospede);
	}

	@RequestMapping(value = "/findName", params = "nome", method = RequestMethod.GET, produces = "application/json")
	public List<Hospede> findByName(@RequestParam(value = "nome")
	String nome) throws ListException {
		return new HospedeController().findByName(nome);
	}

	@RequestMapping(value = "/removeHospede", params = "id", method = RequestMethod.DELETE, produces = "application/json")
	public ResultMessage removeById(@RequestParam(value = "id")
	int id) {
		int result = new HospedeController().removeById(id);

		if (result == 1) {
			resultMessage.setResultMessage(WSConstants.SUCCESS_RESULT);
		} else {
			resultMessage.setResultMessage(WSConstants.FAILURE_RESULT);
		}

		return resultMessage;
	}
}