package com.uolhost.testebackend.testbackEndJava.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class CodinomeResponseMock {

	protected ResponseEntity<String> getLigaDaJusticaPayload() {
		final StringBuilder sb = new StringBuilder();

		sb.append("<liga_da_justica>");
		sb.append("  <codinomes>");
		sb.append("    <codinome>Lanterna Verde</codinome>");
		sb.append("    <codinome>Flash</codinome>");
		sb.append("    <codinome>Aquaman</codinome>");
		sb.append("    <codinome>Batman</codinome>");
		sb.append("    <codinome>Superman</codinome>");
		sb.append("    <codinome>Mulher Maravilha</codinome>");
		sb.append("  </codinomes>");
		sb.append("</liga_da_justica>");
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		return responseEntity;
	}
	
	protected ResponseEntity<String> getVingadoresPayload() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("	\"vingadores\": [{");
		sb.append("			\"codinome\": \"Hulk\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Capitão América\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Pantera Negra\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Homem de Ferro\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Thor\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Feiticeira Escarlate\"");
		sb.append("		},");
		sb.append("		{");
		sb.append("			\"codinome\": \"Visão\"");
		sb.append("		}");
		sb.append("	]");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		return responseEntity;
	}
}
