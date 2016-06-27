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


public class AdvancedBufferedFlatFileSplitter implements Processor{


	private static final int BATCH_SIZE = 20;

	private final Logger logger = LoggerFactory.getLogger(AdvancedBufferedFlatFileSplitter.class);
    
	@Produce(uri = "seda:processChunk?blockWhenFull=true")
	ProducerTemplate processChunk;

	
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
				bufferList.add(sCurrentLine);
				
				if (bufferList.size() == BATCH_SIZE ){
					toBeSent = StringUtils.arrayToDelimitedString(bufferList.toArray(), "\n");
					
					//calculate stats
					mBRead = totalBytes.doubleValue()/1024.0/1024.0;
					elapsed = (double) (System.currentTimeMillis() - start);
					
					headers.put("mbRead", mBRead);
					headers.put("avgMBperSec", mBRead/elapsed*1000);
					headers.put("sample", sCurrentLine);
					processChunk.sendBodyAndHeaders(toBeSent, headers);;
					bufferList.clear();
				}
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
