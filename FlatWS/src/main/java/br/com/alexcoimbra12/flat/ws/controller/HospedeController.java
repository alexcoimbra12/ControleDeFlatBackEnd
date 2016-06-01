package br.com.alexcoimbra12.flat.ws.controller;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

public class HospedeController {
	
	public List<Hospede> findAll() {
		return HospedeDAO.getInstance().findAll();
	}
	public void persistHospede(Hospede hospede){
		HospedeDAO.getInstance().persist(hospede);
	}
}
