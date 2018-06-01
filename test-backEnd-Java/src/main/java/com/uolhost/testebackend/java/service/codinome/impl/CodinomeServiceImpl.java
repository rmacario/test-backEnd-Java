package com.uolhost.testebackend.java.service.codinome.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uolhost.testebackend.java.enums.EOrigemCodinome;
import com.uolhost.testebackend.java.service.codinome.CodinomeService;
import com.uolhost.testebackend.java.service.codinome.dto.LigaDaJustica;
import com.uolhost.testebackend.java.service.codinome.dto.VingadoresVO;

@Service
public class CodinomeServiceImpl implements CodinomeService {

	@Value("#{environment['adress.liga.justica']}")
	private String ligaJusticaAdress;
	
	@Value("#{environment['adress.vingadores']}")
	private String vingadoresAdress;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private final ObjectMapper xmlMapper = new XmlMapper();
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	public CodinomeServiceImpl() {
		super();
	}
	
	public CodinomeServiceImpl(RestTemplate restTemplate, String ligaJusticaAdress, String vingadoresAdress) {
		super();
		this.restTemplate = restTemplate;
		this.ligaJusticaAdress = ligaJusticaAdress;
		this.vingadoresAdress = vingadoresAdress;
	}
	
	
	@Override
	public Set<String> buscarCodinomes(EOrigemCodinome origem) throws JsonParseException, JsonMappingException, IOException {
		
		Set<String> codinomes = null;
		
		switch (origem) {
			case LIGA_JUSTICA:
				codinomes = this.buscarCodinomesLigaJustica();
			break;

			case VINGADORES:
				codinomes = this.buscarCodinomesVingadores();
			break;
			
		default:
			break;
		}
		
		return codinomes;
	}
	
	private Set<String> buscarCodinomesLigaJustica() throws JsonParseException, JsonMappingException, IOException {
		LigaDaJustica ligaDaJusticaResponse = null;
		ResponseEntity<String> response = restTemplate.getForEntity(this.ligaJusticaAdress, String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ligaDaJusticaResponse = xmlMapper.readValue(response.getBody(), LigaDaJustica.class);
		}
		
		return ligaDaJusticaResponse.getCodinomes();
	}
	
	private Set<String> buscarCodinomesVingadores() throws JsonParseException, JsonMappingException, IOException {
		VingadoresVO vingadoresResponse = null;
		ResponseEntity<String> response = restTemplate.getForEntity(this.vingadoresAdress, String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			vingadoresResponse = objectMapper.readValue(response.getBody(), VingadoresVO.class);
		}
		
		Set<String> retorno = new HashSet<String>();
		vingadoresResponse.getVingadores().forEach(vingador -> retorno.add(vingador.getCodinome()));
		
		return retorno;
	}
}
