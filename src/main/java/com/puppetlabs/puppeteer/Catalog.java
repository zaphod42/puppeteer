package com.puppetlabs.puppeteer;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;


public class Catalog {
	
	public JsonNode externalCatalog;

	public Catalog() {}
	
	public Catalog(String json) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.externalCatalog = mapper.readTree(json);
	}
}
