package br.com.alexcoimbra12.flat.ws.hospede.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.alexcoimbra12.flat.ws.dao.HospedeDAO;
import br.com.alexcoimbra12.flat.ws.model.Hospede;
import br.com.alexcoimbra12.flat.ws.resources.HospedeWS;

public class HospedeTest {

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
	
	
	@Test
	public void salvarHospede(){
		HospedeDAO dao = new HospedeDAO();
		dao.persist(hospede);
		dao.persist(hospede2);
		assertTrue(true);
	}
	
	@Test
	public void buscarHospede(){
		HospedeWS hospedeWS = new HospedeWS();
		List<Hospede> hospedeList = new ArrayList<Hospede>();
		hospedeList = hospedeWS.findAll();
		System.out.println(hospedeList);
		assertTrue(true);
	}
}
