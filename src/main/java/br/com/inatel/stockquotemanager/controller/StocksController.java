package br.com.inatel.stockquotemanager.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.stockquotemanager.dto.StockApiDto;
import br.com.inatel.stockquotemanager.dto.StockDto;
import br.com.inatel.stockquotemanager.model.Quotes;
import br.com.inatel.stockquotemanager.model.form.StockQuoteForm;
import br.com.inatel.stockquotemanager.service.QuoteService;
import br.com.inatel.stockquotemanager.service.StockServices;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StocksController {

	@Autowired
	private StockServices stockServices;

	@Autowired
	private QuoteService quoteService;

	Logger log = LoggerFactory.getLogger(StocksController.class);

	@GetMapping("/{id}")
	public ResponseEntity<?> listById(@PathVariable String id) {
		log.debug("Request {}", id);
		List<Quotes> quotes = quoteService.findByStockId(id);
		if (quotes.isEmpty()) {
			log.debug("Stock's name error");
			return ((BodyBuilder) ResponseEntity.notFound()).body("Stock's name error");
		}
		return ResponseEntity.ok(new StockDto(id, quotes));
	}

	@GetMapping
	public ResponseEntity<List<StockDto>> listAll() {
		List<StockApiDto> stocksApiDto = stockServices.listAll();
		List<StockDto> stocksDto = new ArrayList<StockDto>();
		stocksApiDto.forEach(s -> {
			List<Quotes> quotes = quoteService.findByStockId(s.getId());
			stocksDto.add(new StockDto(s.getId(), quotes));
		});
		log.debug("Take all stocks");
		return ResponseEntity.ok().body(stocksDto);
	}

	@PostMapping
	public ResponseEntity<?> posting(@RequestBody @Valid StockQuoteForm form,
			UriComponentsBuilder uriComponentsBuilder) {

		StockApiDto stockApiDto = stockServices.getById(form.getId());
		if (stockApiDto == null) {
			log.debug("Stock's name error");
			return ((BodyBuilder) ResponseEntity.notFound()).body("Stock's name error");
		}

		quoteService.quoteSave(form.convert());

		URI uri = uriComponentsBuilder.path("/quote/{id}").buildAndExpand(form.getId()).toUri();

		List<Quotes> listQuote = quoteService.findByStockId(form.getId());

		log.debug("Request {}", listQuote);

		return ResponseEntity.created(uri).body(new StockDto(form.getId(), listQuote));
	}

}
