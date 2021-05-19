package br.com.inatel.stockquotemanager.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.inatel.stockquotemanager.model.Quotes;

public class StockDto {
	private String id;
	private Map<String, String> quoteMap = new HashMap<String, String>();

	public StockDto(String id, List<Quotes> quotes) {
		this.id = id;
		mapQuotes(quotes);
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getQuoteMap() {
		return quoteMap;
	}

	public void mapQuotes(List<Quotes> quotes) {
		quotes.forEach(q -> {
			String data = q.getTimeNow().toString();
			String price = q.getPrice().toBigInteger().toString();
			this.quoteMap.put(data, price);
		});
	}

}
