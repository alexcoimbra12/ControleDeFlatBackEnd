package br.com.alexcoimbra12.flat.ws.imobiliaria.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.alexcoimbra12.flat.ws.dao.ImobiliariaDAO;
import br.com.alexcoimbra12.flat.ws.model.Imobiliaria;
import br.com.alexcoimbra12.flat.ws.resources.ImobiliariaWS;

public class ImobiliariaTest {


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
	
	@Test
	public void salvaImobiliaria(){
		
		ImobiliariaDAO.getInstance().persist(imobiliaria);
		ImobiliariaDAO.getInstance().persist(imobiliaria2);
		
		assertTrue(true);
	}
	
	@Test
	public void listImobiliaria(){
		ImobiliariaWS imobiliariaWS = new ImobiliariaWS();
		List<Imobiliaria> imobiliariaList = new ArrayList<Imobiliaria>();
		imobiliariaList = imobiliariaWS.findAll();
		System.out.println(imobiliariaList.toString());
		
		assertTrue(true);
	}
}
