package com.puppetlabs.puppeteer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

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
        Collections.addAll(singletons, new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        return singletons;
	}
}
