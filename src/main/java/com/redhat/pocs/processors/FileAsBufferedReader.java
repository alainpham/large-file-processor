package com.redhat.pocs.processors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileAsBufferedReader implements Processor{
	
	private String folder;
	private String fileName;
	
	@Override
	public void process(Exchange exchange) throws Exception {
        folder = exchange.getIn().getHeader("folder", String.class);
        fileName =  exchange.getIn().getHeader("fileName", String.class);
        BufferedReader br = new BufferedReader(new FileReader(new File(folder+"/"+fileName)),8192);
        exchange.getIn().setBody(br);
	}

	
}
