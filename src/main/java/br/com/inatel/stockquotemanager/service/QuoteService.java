package br.com.inatel.stockquotemanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inatel.stockquotemanager.controller.StocksController;
import br.com.inatel.stockquotemanager.model.Quotes;
import br.com.inatel.stockquotemanager.repository.QuotesRepository;

@Service
public class QuoteService {
	@Autowired
	QuotesRepository quotesRepository;

	Logger log = LoggerFactory.getLogger(StocksController.class);

	public void quoteSave(List<Quotes> quotes) {

		quotesRepository.saveAll(quotes);
	}

	public List<Quotes> findByStockId(String id) {
		return quotesRepository.findByStockId(id);
	}

}
