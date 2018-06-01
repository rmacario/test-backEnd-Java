package com.uolhost.testebackend.testbackEndJava.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.uolhost.testebackend.java.service.codinome.CodinomeService;
import com.uolhost.testebackend.java.service.codinome.EOrigemCodinome;
import com.uolhost.testebackend.java.service.codinome.impl.CodinomeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CodinomeServiceTest {

	@Mock
	private RestTemplate restTemplate;

	
	@Test
	public void deveRetornarSeisCodinomesLigaDaJustica() {
		
		final String ligaJusticaAdress = "";
		CodinomeService codinomeservice = new CodinomeServiceImpl(restTemplate, ligaJusticaAdress, null);
		
		try {
			when(restTemplate.getForEntity(ligaJusticaAdress, String.class)).thenReturn(this.getLigaDaJusticaPayload());

			final Set<String> retorno = codinomeservice.buscarCodinomes(EOrigemCodinome.LIGA_JUSTICA);

			assertNotNull(retorno);
			assertTrue(retorno.size() == 6);

		} catch (Exception e) {
			fail("Falha ao obter codinomes liga da justica");
		}
	}
	
	@Test
	public void deveRetornarSeteCodinomesVingadores() {
		
		final String vingadoresAdress = "";
		CodinomeService codinomeservice = new CodinomeServiceImpl(restTemplate, null, vingadoresAdress);
		
		try {
			when(restTemplate.getForEntity(vingadoresAdress, String.class)).thenReturn(this.getVingadoresPayload());

			final Set<String> retorno = codinomeservice.buscarCodinomes(EOrigemCodinome.VINGADORES);

			assertNotNull(retorno);
			assertTrue(retorno.size() == 7);

		} catch (Exception e) {
			fail("Falha ao obter codinomes liga da justica");
		}
	}

	
	private ResponseEntity<String> getVingadoresPayload() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("	\"vingadores\": [{");
		sb.append("			\"codinome\": \"Hulk\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Capitão América\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Pantera Negra\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Homem de Ferro\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Thor\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Feiticeira Escarlate\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Visão\"");
		sb.append("		}");
		sb.append("	]");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		return responseEntity;
	}
	
	private ResponseEntity<String> getLigaDaJusticaPayload() {
		final StringBuilder sb = new StringBuilder();

		sb.append("<liga_da_justica>");
		sb.append("  <codinomes>");
		sb.append("    <codinome>Lanterna Verde</codinome>");
		sb.append("    <codinome>Flash</codinome>");
		sb.append("    <codinome>Aquaman</codinome>");
		sb.append("    <codinome>Batman</codinome>");
		sb.append("    <codinome>Superman</codinome>");
		sb.append("    <codinome>Mulher Maravilha</codinome>");
		sb.append("  </codinomes>");
		sb.append("</liga_da_justica>");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		return responseEntity;
	}
}
