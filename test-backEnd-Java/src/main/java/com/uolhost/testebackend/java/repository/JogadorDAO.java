package com.uolhost.testebackend.java.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uolhost.testebackend.java.model.Jogador;

public interface JogadorDAO extends CrudRepository<Jogador, Long>{
	
    @Query(value = "SELECT codinome FROM Jogador")
    public Set<String> findCodinomes();
}
