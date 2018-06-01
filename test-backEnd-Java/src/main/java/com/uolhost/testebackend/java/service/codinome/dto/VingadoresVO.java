package com.uolhost.testebackend.java.service.codinome.dto;

import java.util.Set;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class VingadoresVO {

	private Set<Vingadores> vingadores;

	
	public Set<Vingadores> getVingadores() {
		return vingadores;
	}

	public void setVingadores(Set<Vingadores> vingadores) {
		this.vingadores = vingadores;
	}

}
