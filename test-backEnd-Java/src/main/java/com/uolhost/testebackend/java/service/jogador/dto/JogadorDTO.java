package com.uolhost.testebackend.java.service.jogador.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.uolhost.testebackend.java.enums.EOrigemCodinome;

public class JogadorDTO {

	@NotNull(message = "O campo nome deve ser preenchido.")
	private String nome;
	
	@NotNull(message = "O campo email deve ser preenchido.")
	private String email;
	
	@Pattern(regexp = "[0-9]{8,9}", message = "O campo telefone deve ser composto por ate 9 caracteres e todos numericos.")
	private String telefone;
	
	@NotNull(message = "Necessario informar uma lista para este jogador.")
	private EOrigemCodinome listaOrigem;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public EOrigemCodinome getListaOrigem() {
		return listaOrigem;
	}
	public void setListaOrigem(EOrigemCodinome listaOrigem) {
		this.listaOrigem = listaOrigem;
	}
}
