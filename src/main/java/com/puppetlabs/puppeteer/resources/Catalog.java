package com.puppetlabs.puppeteer.resources;

import org.codehaus.jackson.JsonNode;

public class Catalog {
	private final JsonNode data;

	public Catalog(JsonNode data) {
		this.data = data;
	}
	
	public JsonNode getTags() {
		return data.get("tags");
	}
	
	public String getName() {
		return data.get("name").asText();
	}
	
	public int getVersion() {
		return data.get("version").asInt();
	}
	
	public String getEnvironment() {
		return data.get("environment").asText();
	}
	
	public JsonNode getResources() {
		return data.get("resources");
	}
	
	public JsonNode getEdges() {
		return data.get("edges");
	}
	
	public JsonNode getClasses() {
		return data.get("classes");
	}
}
