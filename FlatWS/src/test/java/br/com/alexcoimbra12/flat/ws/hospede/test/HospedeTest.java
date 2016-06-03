package br.com.alexcoimbra12.flat.ws.hospede.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.exception.ListException;
import br.com.alexcoimbra12.flat.ws.model.Hospede;
import br.com.alexcoimbra12.flat.ws.resources.HospedeWS;
import br.com.alexcoimbra12.flat.ws.util.ResultMessage;

public class HospedeTest {

	private static Hospede hospede = new Hospede();
	private static Hospede hospede2 = new Hospede();
	
	private HospedeWS hospedeWS;
	
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
		hospedeWS = new HospedeWS();
	}
	
	
	@Test
	public void salvarHospede(){
		ResultMessage message = hospedeWS.persistHospede(hospede);
		ResultMessage message2 = hospedeWS.persistHospede(hospede2);
		assertEquals("success", message.getResultMessage());
		assertEquals("success", message2.getResultMessage());
	}
	
	@Test
	public void editarHospede() throws ListException {
		Hospede hospede = new Hospede();
		hospede = HospedeDAO.getInstance().getById(18);
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
		hospedeWS = null;
	}
}
