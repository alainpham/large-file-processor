package com.redhat.pocs.processors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class AdvancedBufferedFlatFileSplitterNoProcessing implements Processor{


	private final Logger logger = LoggerFactory.getLogger(AdvancedBufferedFlatFileSplitterNoProcessing.class);
    
	
	@Override
	public void process(Exchange exchange) throws Exception {
		BufferedReader br = null;
		try {
			String folder = exchange.getIn().getHeader("folder", String.class);
			String fileName =  exchange.getIn().getHeader("fileName", String.class);

			String sCurrentLine;
			br = new BufferedReader(new FileReader(folder + "/" + fileName));

			Long start = System.currentTimeMillis();
			Long totalBytes= 0l;
			Double mBRead= 0.0;

			Double elapsed = 0.0;
			Double mBPerSec = 0.0;
			Object toBeSent = null;
			List<String> bufferList = new ArrayList<String>();
			Map<String, Object> headers= new HashMap<String,Object>();
			
			while ((sCurrentLine = br.readLine()) != null) {
				totalBytes+=sCurrentLine.length()+1;
				
			}
			Long end = System.currentTimeMillis();
			logger.info("Total time : " + ((end - start)));


		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (br != null){
					br.close();
				}
			} catch (IOException ex) {
				throw ex;
			}
		}

	}

}
