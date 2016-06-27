package com.redhat.pocs.processors;

import java.io.File;
import java.io.FileInputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileAsStream implements Processor{
	
	private String folder;
	private String fileName;
	
	@Override
	public void process(Exchange exchange) throws Exception {
        folder = exchange.getIn().getHeader("folder", String.class);
        fileName =  exchange.getIn().getHeader("fileName", String.class);
//        exchange.getIn().setBody(new FileInputStream(new File(folder+"/"+fileName)));
        exchange.getIn().setBody(new File(folder+"/"+fileName));
	}

	
}
