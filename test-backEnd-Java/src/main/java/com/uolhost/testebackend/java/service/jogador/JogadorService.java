package com.uolhost.testebackend.java.service.jogador;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uolhost.testebackend.java.exception.CodinomeIndisponivelException;
import com.uolhost.testebackend.java.model.Jogador;
import com.uolhost.testebackend.java.service.jogador.dto.JogadorDTO;

public interface JogadorService {
	
	Jogador salvar(JogadorDTO jogador) throws JsonParseException, JsonMappingException, IOException, CodinomeIndisponivelException;
	
	List<Jogador> findAll();
	
	Jogador findById(Long codigoJogador);
}
