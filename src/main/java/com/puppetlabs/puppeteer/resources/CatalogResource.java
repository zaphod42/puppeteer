package com.puppetlabs.puppeteer.resources;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.puppetlabs.puppeteer.Catalog;
import com.puppetlabs.puppeteer.CatalogSource;
import com.puppetlabs.puppeteer.Node;
import com.sun.jersey.spi.resource.Singleton;

@Path("/catalog")
@Singleton
public class CatalogResource {
	private static final String HIERA_LIB = "/Users/andy/work/hiera/lib";
	private static final String FACTER_LIB = "/Users/andy/work/facter/lib";
	private static final String PUPPET_LIB = "/Users/andy/work/puppet/lib";
	private CatalogSource catalogSource;
	
	public CatalogResource(@Context ServletContext context) {
		System.setProperty("jruby.home", context.getRealPath("WEB-INF/jruby"));
		String puppeteerLib = context.getRealPath("WEB-INF/ruby");
		
		catalogSource = new CatalogSource(puppeteerLib, PUPPET_LIB, FACTER_LIB, HIERA_LIB);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Catalog dunno() {
		return new Catalog("catalog thingy");
	}
	
	@GET
	@Path("{node}")
	@Produces(MediaType.APPLICATION_JSON)
	public Catalog compile(@PathParam("node") Node node) {
		return catalogSource.compile(node);
	}
}
