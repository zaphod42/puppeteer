package com.puppetlabs.puppeteer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


public class ResponseDocument<T> {
	@JsonProperty("document_type")
	public final String type;
	public final T data;
	
	public static <T> ResponseDocument<T> respond(String json, Class<T> type) throws JsonProcessingException, IOException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(json);
		return new ResponseDocument<T>(
				data.get("document_type").asText(),
				type.getConstructor(JsonNode.class).newInstance(data.get("data")));
	}

	public ResponseDocument(String type, T data) {
		this.type = type;
		this.data = data;
	}
}
