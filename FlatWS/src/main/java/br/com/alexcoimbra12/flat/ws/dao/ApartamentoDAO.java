package br.com.alexcoimbra12.flat.ws.dao;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Apartamento;

public interface ApartamentoDAO {
	
	List<Apartamento> findAll() throws ListException;
	
	Apartamento getById(final int id) throws HospedeNullException;
}
