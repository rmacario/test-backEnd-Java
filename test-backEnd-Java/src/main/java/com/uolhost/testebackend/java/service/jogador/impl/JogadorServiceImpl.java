package com.uolhost.testebackend.java.service.jogador.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uolhost.testebackend.java.exception.CodinomeIndisponivelException;
import com.uolhost.testebackend.java.model.Jogador;
import com.uolhost.testebackend.java.repository.JogadorDAO;
import com.uolhost.testebackend.java.service.codinome.CodinomeService;
import com.uolhost.testebackend.java.service.jogador.JogadorService;
import com.uolhost.testebackend.java.service.jogador.dto.JogadorDTO;

@Service
public class JogadorServiceImpl implements JogadorService {

	@Autowired
	private JogadorDAO jogadorDAO;
	
	@Autowired
	private CodinomeService codinomeService;
	
	
	public JogadorServiceImpl() {
		super();
	}
	
	public JogadorServiceImpl(JogadorDAO jogadorDAO, CodinomeService codinomeService) {
		this.jogadorDAO = jogadorDAO;
		this.codinomeService = codinomeService;
	}
	
	
	@Override
	@Transactional
	public Jogador salvar(JogadorDTO jogador) throws JsonParseException, JsonMappingException, IOException, CodinomeIndisponivelException {
		
		Jogador jogadorEntity = new Jogador();
		BeanUtils.copyProperties(jogador, jogadorEntity);
		
		Set<String> codinomes = codinomeService.buscarCodinomes(jogador.getListaOrigem());
		codinomes.removeAll(jogadorDAO.findCodinomes());
		
		if(codinomes.size() > 0) {
			jogadorEntity.setCodinome(codinomes.iterator().next());
			jogadorDAO.save(jogadorEntity);
			
			return jogadorEntity;
			
		} else {
			throw new CodinomeIndisponivelException("Nao ha codinomes disponiveis.");
		}
	}

	@Override
	public List<Jogador> findAll() {
		Iterable<Jogador> it = jogadorDAO.findAll();
		List<Jogador> jogadores = new ArrayList<Jogador>();
		
		for (Jogador jogador : it) {
			jogadores.add(jogador);
		}
		
		return jogadores;
	}

}
