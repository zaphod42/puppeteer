package com.puppetlabs.puppeteer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Catalog {
	
	public String externalCatalog;

	public Catalog() {}
	
	public Catalog(Object object) {
		this.externalCatalog = object.toString();
	}
}
