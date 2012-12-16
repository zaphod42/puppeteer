package com.puppetlabs.puppeteer;

import java.io.PrintWriter;

public class Catalog {

	private final Object externalCatalog;

	public Catalog(Object object) {
		this.externalCatalog = object;
	}

	public void write(PrintWriter writer) {
		writer.print(externalCatalog);
	}

}
