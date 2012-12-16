package com.puppetlabs.puppeteer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.puppetlabs.puppeteer.resources.CatalogResource;

public class Puppeteer extends Application {
	@SuppressWarnings("unchecked")
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		Collections.addAll(resources, CatalogResource.class);
		return resources;
	}

	public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();
        Collections.addAll(singletons, prettyJsonProvider());
        return singletons;
	}

	private org.codehaus.jackson.jaxrs.JacksonJsonProvider prettyJsonProvider() {
		ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.INDENT_OUTPUT, true);
        mapper.setSerializationInclusion(Inclusion.NON_NULL);
        return new org.codehaus.jackson.jaxrs.JacksonJsonProvider(mapper);
	}
}
