package com.uolhost.testebackend.java.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.uolhost.testebackend.java.exception.CodinomeIndisponivelException;
import com.uolhost.testebackend.java.model.Jogador;
import com.uolhost.testebackend.java.service.jogador.JogadorService;
import com.uolhost.testebackend.java.service.jogador.dto.JogadorDTO;

@RestController
@RequestMapping("/jogador")
public class JogadorResource {
	
	@Autowired
	private JogadorService jogadorService;
	

	@RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find() {
		try {
			return ResponseEntity.ok(jogadorService.findAll());
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "/{codigoJogador}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable long codigoJogador) {
		try {
			return ResponseEntity.ok(jogadorService.findById(codigoJogador));
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody JogadorDTO jogador, BindingResult bindingResult,
			UriComponentsBuilder uri) {
		
		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		try {
			Jogador jogadorEntity = jogadorService.salvar(jogador);
			
			UriComponents uc = uri.path("/jogador/{id}").buildAndExpand(jogadorEntity.getCodigoJogador());
			return ResponseEntity.created(uc.toUri()).body(jogadorEntity);
			
		} catch(CodinomeIndisponivelException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
