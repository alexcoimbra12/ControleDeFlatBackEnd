package br.com.alexcoimbra12.flat.ws.dao;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;

public interface HospedeDAO {

	int persist(Hospede hospede);

	List<Hospede> findByName(String nome) throws ListException;

	Hospede getById(final int id) throws HospedeNullException;

	int merge(Hospede hospede);

	int removeById(final int id);

	List<Hospede> findAll() throws ListException;

}
