package com.uolhost.testebackend.testbackEndJava.service;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uolhost.testebackend.java.TestBackEndJavaApplication;
import com.uolhost.testebackend.java.configuration.RestTemplateConfig;
import com.uolhost.testebackend.java.enums.EOrigemCodinome;
import com.uolhost.testebackend.java.exception.CodinomeIndisponivelException;
import com.uolhost.testebackend.java.model.Jogador;
import com.uolhost.testebackend.java.repository.JogadorDAO;
import com.uolhost.testebackend.java.service.codinome.CodinomeService;
import com.uolhost.testebackend.java.service.codinome.impl.CodinomeServiceImpl;
import com.uolhost.testebackend.java.service.jogador.JogadorService;
import com.uolhost.testebackend.java.service.jogador.dto.JogadorDTO;
import com.uolhost.testebackend.java.service.jogador.impl.JogadorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackages = {"com.uolhost.testebackend.java.model"})
@SpringBootTest(classes = TestBackEndJavaApplication.class)
@Import({ JogadorServiceImpl.class, CodinomeServiceImpl.class, RestTemplateConfig.class })
public class JogadorServiceTest extends CodinomeResponseMock {

	@Mock
	private RestTemplate restTemplate;
	
	@Autowired
	private JogadorService jogadorService;
	
	@Autowired
	private JogadorDAO jogadorDao;
	
	
	@Test
	public void deveRetornarZeroRegistros() {
		final List<Jogador> jogadores = jogadorService.findAll();
		assertTrue(jogadores.isEmpty());
	}
	
	@Test
	public void deveRetornarDoisRegistros() {
		final String ligaDaJusticaAdress = "";
		final CodinomeService codinomeService = new CodinomeServiceImpl(restTemplate, ligaDaJusticaAdress, null);
		final JogadorService jogadorService = new JogadorServiceImpl(jogadorDao, codinomeService);
		
		
		JogadorDTO dto1 = new JogadorDTO();
		dto1.setEmail("test@test.com");
		dto1.setNome("Teste Silva");
		dto1.setListaOrigem(EOrigemCodinome.LIGA_JUSTICA);
		dto1.setTelefone("11111111");
		
		
		JogadorDTO dto2 = new JogadorDTO();
		dto2.setEmail("test2@test.com");
		dto2.setNome("Teste2 Silva");
		dto2.setListaOrigem(EOrigemCodinome.LIGA_JUSTICA);
		dto2.setTelefone("22222222");
		
		try {
			when(restTemplate.getForEntity(ligaDaJusticaAdress, String.class)).thenReturn(this.getLigaDaJusticaPayload());
			
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto2);
			
			List<Jogador> jogadores = jogadorService.findAll();
			assertTrue(jogadores.size() == 2);
		
		} catch(Exception e) {
			fail("Falha ao inserir jogadores");
		}
	}
	
	@Test
	public void deveApresentarErroQuandoNaoHouverCodinomesDisponiveis() {
		final String ligaDaJusticaAdress = "";
		final CodinomeService codinomeService = new CodinomeServiceImpl(restTemplate, ligaDaJusticaAdress, null);
		final JogadorService jogadorService = new JogadorServiceImpl(jogadorDao, codinomeService);
		
		
		JogadorDTO dto1 = new JogadorDTO();
		dto1.setEmail("test@test.com");
		dto1.setNome("Teste Silva");
		dto1.setListaOrigem(EOrigemCodinome.LIGA_JUSTICA);
		dto1.setTelefone("11111111");
		
		try {
			when(restTemplate.getForEntity(ligaDaJusticaAdress, String.class)).thenReturn(this.getLigaDaJusticaPayload());
			
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			jogadorService.salvar(dto1);
			
			fail("Necessario que ocorra erro.");
		
		} catch(Exception e) {
			assertTrue(e instanceof CodinomeIndisponivelException);
		}
	}
	
	@Test
	public void deveRetornarUmRegistroFiltrandoPorId() {
		final String ligaDaJusticaAdress = "";
		final CodinomeService codinomeService = new CodinomeServiceImpl(restTemplate, ligaDaJusticaAdress, null);
		final JogadorService jogadorService = new JogadorServiceImpl(jogadorDao, codinomeService);
		
		JogadorDTO dto = new JogadorDTO();
		dto.setEmail("test@test.com");
		dto.setNome("Teste Silva");
		dto.setListaOrigem(EOrigemCodinome.LIGA_JUSTICA);
		dto.setTelefone("11111111");
		
		try {
			when(restTemplate.getForEntity(ligaDaJusticaAdress, String.class)).thenReturn(this.getLigaDaJusticaPayload());
			
			Jogador jogadorSalvo = jogadorService.salvar(dto);
			Jogador jogador = jogadorService.findById(jogadorSalvo.getCodigoJogador());
			
			assertNotNull(jogador);
		
		} catch(Exception e) {
			assertTrue(e instanceof CodinomeIndisponivelException);
		}
	}
	
	@Test
	public void deveRetornarZeroRegistrosFiltrandoPorId() {
		final String ligaDaJusticaAdress = "";
		final CodinomeService codinomeService = new CodinomeServiceImpl(restTemplate, ligaDaJusticaAdress, null);
		final JogadorService jogadorService = new JogadorServiceImpl(jogadorDao, codinomeService);

		try {
			when(restTemplate.getForEntity(ligaDaJusticaAdress, String.class)).thenReturn(this.getLigaDaJusticaPayload());
			
			Jogador jogador = jogadorService.findById(1l);
			assertNull(jogador);
		
		} catch(Exception e) {
			assertTrue(e instanceof CodinomeIndisponivelException);
		}
	}
}
