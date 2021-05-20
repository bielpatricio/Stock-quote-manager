package br.com.inatel.stockquotemanager.controller;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.stockquotemanager.service.StockServices;

@RestController
@RequestMapping("/stockcache")
public class CacheController {

	Logger log = LoggerFactory.getLogger(StocksController.class);

	@Autowired
	public CacheController(StockServices stockServices) {
		stockServices.registerNotification();
	}

	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "stock", allEntries = true),
			@CacheEvict(value = "stockList", allEntries = true) })
	public ResponseEntity<?> cleanCache() {
		log.debug("Clean cache");
		return ResponseEntity.status(204).build();
	}
}
