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
}
