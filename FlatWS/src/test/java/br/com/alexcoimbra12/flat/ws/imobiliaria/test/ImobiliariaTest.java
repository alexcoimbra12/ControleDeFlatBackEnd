package br.com.alexcoimbra12.flat.ws.imobiliaria.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.exception.ImobiliariaNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;
import br.com.alexcoimbra12.flat.ws.resources.ImobiliariaWS;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-contextTest.xml"})
public class ImobiliariaTest {

	@Autowired
	private ImobiliariaDAO dao;
	
	@Autowired
	private ImobiliariaWS imobiliariaWS;
	
	private Logger log = LogManager.getLogger(ImobiliariaTest.class);
	
	@Rule
	public TestName test = new TestName();
	
	
	public static Imobiliaria imobiliaria = new Imobiliaria();
	private static Imobiliaria imobiliaria2 = new Imobiliaria();
	
	
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
		log.debug(String.format("Iniciando teste [%s]", test.getMethodName()));
	}
	
	
	@Test
	public void salvarImobiliaria(){
		ResultMessage message = imobiliariaWS.persistImobiliaria(imobiliaria);
		ResultMessage message2 = imobiliariaWS.persistImobiliaria(imobiliaria2);
		assertEquals("success", message.getResultMessage());
		assertEquals("success", message2.getResultMessage());
	}
	
	@Test
	public void editarImobiliaria() throws ImobiliariaNullException {
		
		Imobiliaria imobiliaria = new Imobiliaria();
		imobiliaria = dao.getById(1);
		imobiliaria.setNome("Imobiliaria TESTE EDITAR");
		ResultMessage message = imobiliariaWS.editImobiliaria(imobiliaria);
		
		assertEquals("success", message.getResultMessage());
	}
	
	@Test
	public void deletarImobiliaria() {
		ResultMessage message = imobiliariaWS.removeById(3);
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
		log.debug(String.format("Finalizando teste [%s]", test.getMethodName()));
	}
}
