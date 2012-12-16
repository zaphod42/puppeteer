package com.puppetlabs.puppeteer.resources;

import org.codehaus.jackson.JsonNode;

public class Catalog {
	public final JsonNode tags;
	public final String name;
	public final int version;
	public final String environment;
	public final JsonNode resources;
	public final JsonNode edges;
	public final JsonNode classes;

	public Catalog(JsonNode data) {
		tags = data.get("tags");
		name = data.get("name").asText();
		version = data.get("version").asInt();
		environment = data.get("environment").asText();
		resources = data.get("resources");
		edges = data.get("edges");
		classes = data.get("classes");
	}
}
