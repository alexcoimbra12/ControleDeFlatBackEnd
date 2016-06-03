package br.com.alexcoimbra12.flat.ws.controller;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

public class ImobiliariaController {
	
	public int persistImobiliaria(Imobiliaria imobiliaria){
		return ImobiliariaDAO.getInstance().persist(imobiliaria);
	}
	public List<Imobiliaria> findByName(String nome) throws ListException{
		return ImobiliariaDAO.getInstance().findByName(nome);
	}
	public int removeById(int id){
		return ImobiliariaDAO.getInstance().removeById(id);
	}
	public int editImobiliaria(Imobiliaria imobiliaria) {
		return ImobiliariaDAO.getInstance().merge(imobiliaria);
	}
	public Imobiliaria getById(int id) throws ListException{
		return ImobiliariaDAO.getInstance().getById(id);
	}
}
