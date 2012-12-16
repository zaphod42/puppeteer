package com.puppetlabs.puppeteer.resources;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonValue;

public class Catalog {
	public final List<Tag> tags;
	public final String name;
	public final int version;
	public final String environment;
	public final List<Resource> resources;
	public final JsonNode edges;
	public final JsonNode classes;

	public Catalog(JsonNode data) {
		tags = Tag.from(data.get("tags"));
		name = data.get("name").asText();
		version = data.get("version").asInt();
		environment = data.get("environment").asText();
		resources = Resource.from(data.get("resources"));
		edges = data.get("edges");
		classes = data.get("classes");
	}
	
	public static class Tag {
		public final String tag;
		
		public Tag(String tag) {
			this.tag = tag;
		}

		public static List<Tag> from(Iterable<JsonNode> data) {
			List<Tag> tags = new ArrayList<Tag>();
			for (JsonNode tagData : data) {
				tags.add(new Tag(tagData.asText()));
			}
			return tags;
		}
		
		@JsonValue
		@Override
		public String toString() {
			return tag;
		}
	}
	
	public static class Resource {
		public final String type;
		public final String title;
		public final List<Tag> tags;
		public final boolean exported;
		public final String file;
		public final Integer line;
		public final JsonNode parameters;

		public Resource(JsonNode data) {
			type = data.get("type").asText();
			title = data.get("title").asText();
			tags = Tag.from(data.get("tags"));
			exported = data.get("exported").asBoolean();
			file = maybeText(data.get("file"));
			line = maybeInt(data.get("line"));
			parameters = data.get("parameters");
		}

		public static List<Resource> from(Iterable<JsonNode> data) {
			List<Resource> resources = new ArrayList<Resource>();
			for (JsonNode resourceData : data) {
				resources.add(new Resource(resourceData));
			}
			return resources;
		}
		
		public static String maybeText(JsonNode node) {
			if (node != null) {
				return node.asText();
			}
			return null;
		}
		
		public static Integer maybeInt(JsonNode node) {
			if (node != null) {
				return node.asInt();
			}
			return null;
		}
	}
}
