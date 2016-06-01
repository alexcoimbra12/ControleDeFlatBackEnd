package br.com.alexcoimbra12.flat.ws.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexcoimbra12.flat.ws.controller.HospedeController;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

@RestController
@RequestMapping(value = "/hospede")
public class HospedeWS {

	@RequestMapping(value = "/getHospedes", method = RequestMethod.GET, produces = "application/json")
	public List<Hospede> findAll() {
		return new HospedeController().findAll();
	}
	
	@RequestMapping(value = "/saveHospede", method = RequestMethod.POST)
	public void persistHospede(Hospede hospede){
		new HospedeController().persistHospede(hospede);
	}
}