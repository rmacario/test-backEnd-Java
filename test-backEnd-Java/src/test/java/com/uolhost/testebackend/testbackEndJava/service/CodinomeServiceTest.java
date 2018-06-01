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
import org.springframework.web.client.RestTemplate;

import com.uolhost.testebackend.java.enums.EOrigemCodinome;
import com.uolhost.testebackend.java.service.codinome.CodinomeService;
import com.uolhost.testebackend.java.service.codinome.impl.CodinomeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CodinomeServiceTest extends CodinomeResponseMock {

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
}
