package br.com.alexcoimbra12.flat.ws.imobiliaria.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;
import br.com.alexcoimbra12.flat.ws.resources.ImobiliariaWS;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

public class ImobiliariaTest {


	public static Imobiliaria imobiliaria = new Imobiliaria();
	private static Imobiliaria imobiliaria2 = new Imobiliaria();
	
	private ImobiliariaWS imobiliariaWS;
	
	@BeforeClass
	public static void setup() {
		
		imobiliaria.setNome("Imobiliaria1");
		imobiliaria.setEndereco("Av Rio Branco");
		imobiliaria.setNomeCorretor("Corretor1");
		imobiliaria.setNumCreci("352025");
		imobiliaria.setTelContato("55555555");
		
		imobiliaria2.setNome("Imobiliaria2");
		imobiliaria2.setEndereco("Av Rio Branco");
		imobiliaria2.setNomeCorretor("Corretor2");
		imobiliaria2.setNumCreci("352025");
		imobiliaria2.setTelContato("55555555");
	}
	
	@Before
	public void before(){
		imobiliariaWS = new ImobiliariaWS();
	}
	
	
	@Test
	public void salvarImobiliaria(){
		ResultMessage message = imobiliariaWS.persistImobiliaria(imobiliaria);
		ResultMessage message2 = imobiliariaWS.persistImobiliaria(imobiliaria2);
		assertEquals("success", message.getResultMessage());
		assertEquals("success", message2.getResultMessage());
	}
	
	@Test
	public void editarImobiliaria() throws ListException {
		
		Imobiliaria imobiliaria = new Imobiliaria();
		imobiliaria = ImobiliariaDAO.getInstance().getById(1);
		imobiliaria.setNome("Imobiliaria TESTE EDITAR");
		ResultMessage message = imobiliariaWS.editImobiliaria(imobiliaria);
		
		assertEquals("success", message.getResultMessage());
	}
	
	@Test
	public void deletarImobiliaria() {
		ResultMessage message = imobiliariaWS.removeById(2);
		assertEquals("success", message.getResultMessage());
	}
	
	@Test
	public void findByName() throws ListException{
		List<Imobiliaria> imobiliariaList = new ArrayList<Imobiliaria>();
		imobiliariaList = imobiliariaWS.findByName("a");
		assertFalse(imobiliariaList.isEmpty());
	}
	
	@After
	public void teardown() {
		imobiliariaWS = null;
	}
}
