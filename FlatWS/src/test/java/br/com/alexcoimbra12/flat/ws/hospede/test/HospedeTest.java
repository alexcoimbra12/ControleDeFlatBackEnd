package br.com.alexcoimbra12.flat.ws.hospede.test;

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

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.exception.HospedeNullException;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;
import br.com.alexcoimbra12.flat.ws.resources.HospedeWS;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-contextTest.xml"})
public class HospedeTest {
	
	@Autowired
	private HospedeDAO dao;
	
	@Autowired
	private HospedeWS hospedeWS;
	
	private Logger log = LogManager.getLogger(HospedeTest.class);
	
	@Rule
	public TestName test = new TestName();

	private static Hospede hospede = new Hospede();
	private static Hospede hospede2 = new Hospede();
	
	
	@BeforeClass
	public static void setup(){
		hospede.setNome("Teste1");
		hospede.setCpf("111");
		hospede.setTelefone("1123222");
		
		hospede2.setNome("Teste2");
		hospede2.setCpf("111");
		hospede2.setTelefone("1123222");
	}
	
	@Before
	public void before(){
		log.debug(String.format("Iniciando teste [%s]", test.getMethodName()));

	}
	
	
	@Test
	public void salvarHospede(){
		ResultMessage message = hospedeWS.persistHospede(hospede);
		ResultMessage message2 = hospedeWS.persistHospede(hospede2);
		assertEquals("success", message.getResultMessage());
		assertEquals("success", message2.getResultMessage());
	}
	
	@Test
	public void editarHospede() throws HospedeNullException {
		Hospede hospede = new Hospede();
		hospede = dao.getById(18);
		hospede.setCpf("11111111111");
		ResultMessage message = hospedeWS.editHospede(hospede);
		
		assertEquals("success", message.getResultMessage());
	}
	
	@Test
	public void deletarHospede() {
		ResultMessage message = hospedeWS.removeById(19);
		assertEquals("success", message.getResultMessage());
	}
	
	@Test
	public void findByName() throws ListException{
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		hospedeList = hospedeWS.findByName("a");
		assertFalse(hospedeList.isEmpty());
	}
	
	@After
	public void teardown() {
		log.debug(String.format("Finalizando teste [%s]", test.getMethodName()));
	}
}
