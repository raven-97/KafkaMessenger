package org.sedai.testbed.kafka.model;


import java.util.HashMap;
import java.util.Map;

public class StockDetails {
	
	
	private Map<String, String> quoteMap;
	
	public StockDetails()
	{
		quoteMap = new HashMap();
	}
	


	public Map<String, String> getQuoteMap() {
		
		return quoteMap;
	}

	public void setQuoteMap(Map<String, String> quoteMap) {
		this.quoteMap = quoteMap;
	}

}
