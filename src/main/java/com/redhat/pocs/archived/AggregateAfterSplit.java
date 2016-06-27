package com.redhat.pocs.archived;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.pocs.model.WireTransfer;


public class AggregateAfterSplit implements AggregationStrategy{
    private final Logger logger = LoggerFactory.getLogger(AggregateAfterSplit.class);

	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		logger.info("starting aggregation..");
		
		List<WireTransfer> wl = null;
		
		//Initialization of the aggregated list or getting existing one
		if (oldExchange==null){
			oldExchange = newExchange;
			wl = new ArrayList<WireTransfer>();
			oldExchange.getIn().setHeader("elList", wl);
		}else{
			wl = oldExchange.getIn().getHeader("elList",List.class);
		}
		
		Object o = newExchange.getIn().getBody(ArrayList.class);
		if (o!=null){
			logger.info("aggregating : " + o.getClass().getName());
			List<WireTransfer> newWl = (List<WireTransfer>) o;
			wl.addAll(newWl);
			
		}
		o = newExchange.getIn().getBody(WireTransfer.class);
		if (o!=null){
			logger.info("aggregating : " + o.getClass().getName());
			wl.add( (WireTransfer) o);
		}
		return oldExchange;
	}

	
}
