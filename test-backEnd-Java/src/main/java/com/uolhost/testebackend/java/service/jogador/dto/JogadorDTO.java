package com.uolhost.testebackend.java.service.jogador.dto;

import com.uolhost.testebackend.java.enums.EOrigemCodinome;

public class JogadorDTO {

	private String nome;
	private String email;
	private String telefone;
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
