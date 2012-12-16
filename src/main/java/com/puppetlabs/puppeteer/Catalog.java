package com.puppetlabs.puppeteer;


public class Catalog {
	
	public String externalCatalog;

	public Catalog() {}
	
	public Catalog(Object object) {
		this.externalCatalog = object.toString();
	}
}
