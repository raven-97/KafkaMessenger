package org.sedai.testbed.kafka.springbootkafkaproducerexample.resource;

import org.sedai.testbed.kafka.springbootkafkaproducerexample.model.StockDetails;

import jdk.internal.org.jline.utils.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.YahooFinance;
import yahoofinance.Stock;

@RestController
@RequestMapping("kafkaStock")
public class StockResource {
	
	@Autowired
    private KafkaTemplate<String, StockDetails> kafkaTemplate;

    private static final String TOPIC = "KafkaStockMessage";
    
    @GetMapping("/publish/StockPayload")
    public String post() {
    	
    	StockDetails sd = quoteDetails();

        kafkaTemplate.send(TOPIC, sd);

        return "Published successfully";
    }
    public static StockDetails quoteDetails()
    {
    	
    	String[] symbols = new String[] {"FB", "MSFT", "IBM"};
    	Map<String, String> quoteDetails = new HashMap<String, String>();
    	StockDetails sd = new StockDetails();
    	
    	
    	for(String symbol : symbols) {
    		
    		Stock stock;
			try {
				stock = YahooFinance.get(symbol);
				BigDecimal price = stock.getQuote(true).getPrice();
	    		quoteDetails.put(symbol, price.toPlainString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in YahooFinance api call");
			}
    		
    		
    	}
    	sd.setQuoteMap(quoteDetails);
    	return sd;
    	
    	
    	
    
    	
    }
    

}
