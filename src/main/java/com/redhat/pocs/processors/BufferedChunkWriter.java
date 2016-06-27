package com.redhat.pocs.processors;

import java.io.BufferedWriter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferedChunkWriter implements Processor{
	private final Logger logger = LoggerFactory.getLogger(BufferedChunkWriter.class);

	
	public BufferedChunkWriter() {
		super();
		logger.info("Created BufferedChunkWriter");
	}


	@Override
	public void process(Exchange exchange) throws Exception {
		BufferedWriter bw = exchange.getIn().getHeader("writer", BufferedWriter.class);
			bw.append(exchange.getIn().getBody(String.class));
			bw.flush();
	}

}
