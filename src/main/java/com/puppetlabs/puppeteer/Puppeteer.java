package com.puppetlabs.puppeteer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Puppeteer extends HttpServlet {
	private static final String HIERA_LIB = "/Users/andy/work/hiera/lib";
	private static final String FACTER_LIB = "/Users/andy/work/facter/lib";
	private static final String PUPPET_LIB = "/Users/andy/work/puppet/lib";
	private static final long serialVersionUID = 1L;
	private Puppet puppet;

	@Override
	public void init() throws ServletException {
		super.init();
		System.setProperty("jruby.home", getServletContext().getRealPath("WEB-INF/jruby"));
		String puppeteerLib = getServletContext().getRealPath("WEB-INF/ruby");
		
		puppet = new Puppet(puppeteerLib, PUPPET_LIB, FACTER_LIB, HIERA_LIB);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		puppet.compile(new Node("aparker")).write(response.getWriter());
		response.setContentType("text/plain");
		response.setStatus(200);
	}
}
