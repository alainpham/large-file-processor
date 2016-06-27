package com.redhat.pocs.processors;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OpenBufferedWriter implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		BufferedWriter bw = null;
		String folder = exchange.getIn().getHeader("folder", String.class);
		String fileName =  exchange.getIn().getHeader("fileName", String.class);

		bw = new BufferedWriter(new FileWriter(folder + "/" + fileName + ".out"));

		exchange.getIn().setHeader("writer", bw);
		
	}

}
