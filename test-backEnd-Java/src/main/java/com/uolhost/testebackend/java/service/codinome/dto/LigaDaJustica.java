package com.uolhost.testebackend.java.service.codinome.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LigaDaJustica {

	private Set<String> codinomes;

	
	public Set<String> getCodinomes() {
		return codinomes;
	}

	public void setCodinomes(Set<String> codinomes) {
		this.codinomes = codinomes;
	}
}
