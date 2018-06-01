package com.uolhost.testebackend.java.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uolhost.testebackend.java.service.jogador.JogadorService;

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
	
	public ResponseEntity<?> save() {
		try {
			return null;
		} catch(Exception e) {
			return null;
		}
	}
	
}
