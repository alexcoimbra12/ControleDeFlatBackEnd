package br.com.alexcoimbra12.flat.ws.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexcoimbra12.flat.ws.controller.ImobiliariaController;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

@RestController
@RequestMapping(value = "/imobiliaria")
public class ImobiliariaWS {
	
	@RequestMapping(value = "/getImobiliaria", method = RequestMethod.GET, produces = "application/json")
	public List<Imobiliaria> findAll(){
		return new ImobiliariaController().findAll();
	}
	
	@RequestMapping(value = "/findName", params = "nome", method = RequestMethod.GET, produces = "application/json")
	public List<Imobiliaria> findByName(@RequestParam(value = "nome") String nome) throws ListException{
		return new ImobiliariaController().findByName(nome);
	}
	
}
