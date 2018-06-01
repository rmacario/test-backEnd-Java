package com.uolhost.testebackend.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uolhost.testebackend.java.enums.EOrigemCodinome;

@Entity
@Table(name="TB_JOGADOR")
public class Jogador {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigoJogador;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@Column
	private String telefone;
	
	@Column
	private String codinome;
	
	@Column
	private EOrigemCodinome listaOrigem;
	
	
	public Jogador() {
		super();
	}
	
	public Jogador(String codinome) {
		this.codinome = codinome;
	}
	

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

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public EOrigemCodinome getListaOrigem() {
		return listaOrigem;
	}
	
	public void setListaOrigem(EOrigemCodinome listaOrigem) {
		this.listaOrigem = listaOrigem;
	}
}
