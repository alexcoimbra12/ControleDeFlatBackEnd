package br.com.alexcoimbra12.flat.ws.controller;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

public class HospedeController {
	
	public int persistHospede(Hospede hospede){
		return HospedeDAO.getInstance().persist(hospede);
	}
	public List<Hospede> findByName(String nome) throws ListException{
		return HospedeDAO.getInstance().findByName(nome);
	}
	public int removeById(int id){
		return HospedeDAO.getInstance().removeById(id);
	}
	public int editHospede(Hospede hospede) {
		return HospedeDAO.getInstance().merge(hospede);
	}
	public Hospede getById(int id) throws ListException{
		return HospedeDAO.getInstance().getById(id);
	}
}
