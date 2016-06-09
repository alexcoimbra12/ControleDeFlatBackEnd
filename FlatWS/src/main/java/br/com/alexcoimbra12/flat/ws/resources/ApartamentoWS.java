package br.com.alexcoimbra12.flat.ws.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexcoimbra12.flat.ws.constants.WSConstants;
import br.com.alexcoimbra12.flat.ws.dao.ApartamentoDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Apartamento;

@RestController
@RequestMapping(value = WSConstants.REQUEST_APARTAMENTO)
public class ApartamentoWS {
	
	@Autowired
	private ApartamentoDAO dao;
	
	@RequestMapping(value = WSConstants.GET_ALL, method = RequestMethod.GET, produces = WSConstants.PRODUCES_JSON)
	public List<Apartamento> findAll() throws ListException{
		return dao.findAll();
	}
}
