package com.puppetlabs.puppeteer;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


public class Catalog {
	
	private JsonNode externalCatalog;

	public Catalog() {}
	
	public Catalog(String json) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.externalCatalog = mapper.readTree(json);
	}
	
	@JsonProperty("document_type")
	public String getDocumentType() {
		return externalCatalog.get("document_type").asText();
	}
	
	public JsonNode getData() {
		return externalCatalog.get("data");
	}
}
