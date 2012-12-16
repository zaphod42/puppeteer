package com.puppetlabs.puppeteer;

import java.util.Arrays;

import org.jruby.CompatVersion;
import org.jruby.embed.ScriptingContainer;

public class CatalogSource {
	private ScriptingContainer ruby;
	private Object puppeteer;

	public CatalogSource(String... paths) {
		ruby = rubyInterpreter(paths);
		puppeteer = ruby.runScriptlet("Puppeteer.new");
	}
	
	public Catalog compile(Node node) {
		return new Catalog(ruby.callMethod(puppeteer, "catalog", node.name));
	}
	
	private ScriptingContainer rubyInterpreter(String... paths) {
		ScriptingContainer ruby = new ScriptingContainer();
		ruby.setLoadPaths(Arrays.asList(paths));
		ruby.setCompatVersion(CompatVersion.RUBY1_9);
		ruby.runScriptlet("require 'puppeteer'");
		return ruby;
	}
}
