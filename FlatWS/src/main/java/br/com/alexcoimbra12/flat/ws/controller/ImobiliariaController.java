package br.com.alexcoimbra12.flat.ws.controller;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

public class ImobiliariaController {
	
	public List<Imobiliaria> findAll(){
		return ImobiliariaDAO.getInstance().findAll();
	}
	public List<Imobiliaria> findByName(String nome) throws ListException{
		return ImobiliariaDAO.getInstance().findByName(nome);
	}
}
