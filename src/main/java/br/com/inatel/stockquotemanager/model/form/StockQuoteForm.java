package br.com.inatel.stockquotemanager.model.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.stockquotemanager.model.Quotes;

public class StockQuoteForm {
	@NotEmpty
	@NotNull
	private String id;
	@NotEmpty
	@NotNull
	Map<String, String> quotes = new HashMap<String, String>();

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public String getId() {
		return id;
	}

	public List<Quotes> convert() {
		List<Quotes> quoteList = new ArrayList<>();

		for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
			LocalDate date = LocalDate.parse(quote.getKey());
			BigDecimal price = new BigDecimal(quote.getValue());

			quoteList.add(new Quotes(this.id, date, price));
		}

		return quoteList;
	}

}
