package com.redhat.pocs.processors;



import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessRawFileChunks implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		String[] l = exchange.getIn().getBody(String.class).split("\n");
		for (int i = 0; i < l.length; i++) {
			if (!l[i].endsWith("\n") && i == (l.length-1)){
				exchange.getIn().setBody(exchange.getIn().getBody(String.class) + "\n",String.class);
			};
		}
	}

}
