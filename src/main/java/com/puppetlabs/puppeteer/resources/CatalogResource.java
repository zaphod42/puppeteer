package com.puppetlabs.puppeteer.resources;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;

import com.puppetlabs.puppeteer.ResponseDocument;
import com.puppetlabs.puppeteer.RubyCatalogCompile;
import com.puppetlabs.puppeteer.Node;
import com.sun.jersey.spi.resource.Singleton;

@Path("/catalog")
@Singleton
public class CatalogResource {
	private static final String HIERA_LIB = "/Users/andy/work/hiera/lib";
	private static final String FACTER_LIB = "/Users/andy/work/facter/lib";
	private static final String PUPPET_LIB = "/Users/andy/work/puppet/lib";
	private RubyCatalogCompile catalogSource;
	
	public CatalogResource(@Context ServletContext context) {
		System.setProperty("jruby.home", context.getRealPath("WEB-INF/jruby"));
		String puppeteerLib = context.getRealPath("WEB-INF/ruby");
		
		catalogSource = new RubyCatalogCompile(puppeteerLib, PUPPET_LIB, FACTER_LIB, HIERA_LIB);
	}
	
	@GET
	@Path("{environment}/{node}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDocument<Catalog> compile(@PathParam("environment") Environment environment, @PathParam("node") Node node) throws JsonProcessingException, IOException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return catalogSource.compile(environment, node);
	}
}
