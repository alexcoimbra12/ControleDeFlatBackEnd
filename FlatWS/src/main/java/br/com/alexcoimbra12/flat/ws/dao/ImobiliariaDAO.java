package br.com.alexcoimbra12.flat.ws.dao;

import java.util.List;

import br.com.alexcoimbra12.flat.ws.exception.ImobiliariaNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;

public interface ImobiliariaDAO {

	int persist(Imobiliaria Imobiliaria);

	List<Imobiliaria> findByName(String nome) throws ListException;

	Imobiliaria getById(final int id) throws ImobiliariaNullException;

	int merge(Imobiliaria imobiliaria);

	int removeById(final int id);

	List<Imobiliaria> findAll() throws ListException;

}
