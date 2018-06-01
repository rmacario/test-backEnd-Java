package com.uolhost.testebackend.java.service.codinome;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uolhost.testebackend.java.enums.EOrigemCodinome;

public interface CodinomeService {

	Set<String> buscarCodinomes(EOrigemCodinome origem) throws JsonParseException, JsonMappingException, IOException;
}
