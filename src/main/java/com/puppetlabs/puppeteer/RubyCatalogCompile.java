package com.puppetlabs.puppeteer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.jruby.CompatVersion;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;

import com.puppetlabs.puppeteer.resources.Catalog;
import com.puppetlabs.puppeteer.resources.Environment;

public class RubyCatalogCompile {
	private final String[] paths;
	private final Map<Environment, CatalogSource> sources = new HashMap<Environment, CatalogSource>();

	public RubyCatalogCompile(String... paths) {
		this.paths = paths;
	}
	
	public ResponseDocument<Catalog> compile(Environment environment, Node node) throws JsonProcessingException, IOException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CatalogSource puppeteer = sourceFor(environment);
		return ResponseDocument.respond(puppeteer.catalog(node.name), Catalog.class);
	}
	
	private CatalogSource sourceFor(Environment environment) {
		if (sources.containsKey(environment)) {
			return sources.get(environment);
		} else {
			CatalogSource puppeteer = rubyInterpreter(environment, paths);
			sources.put(environment, puppeteer);
			return puppeteer;
		}
	}

	private CatalogSource rubyInterpreter(Environment environment, String... paths) {
		ScriptingContainer ruby = new ScriptingContainer(LocalContextScope.SINGLETHREAD);
		ruby.setLoadPaths(Arrays.asList(paths));
		ruby.setCompatVersion(CompatVersion.RUBY1_9);
		ruby.runScriptlet("require 'environment_specific_puppeteer'");
		Object puppeteerClass = ruby.get("EnvironmentSpecificPuppeteer");
		return ruby.callMethod(puppeteerClass, "new", environment.name, CatalogSource.class);
	}
}
