package br.com.inatel.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.inatel.stockquotemanager.controller.StocksController;
import br.com.inatel.stockquotemanager.dto.StockApiDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServices {
	private String url = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

	Logger log = LoggerFactory.getLogger(StocksController.class);

	@Cacheable(value = "stockList")
	public List<StockApiDto> listAll() {
		log.debug("Get all stock from external API");
		String URL = url + "/stock";
		StockApiDto[] stockApiDto = restTemplate.getForObject(URL, StockApiDto[].class);

		return Arrays.asList(stockApiDto);
	}

	@Cacheable(value = "stock")
	public StockApiDto getById(String id) {
		log.debug("Get one stock from external API");
		String URL = url + "/stock/" + id;
		StockApiDto stockApiDto = restTemplate.getForObject(URL, StockApiDto.class);

		return stockApiDto;
	}

	public void registerNotification() {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject data = new JSONObject();
		data.put("host", "localhost");
		data.put("port", "8081");
		HttpEntity<String> request = new HttpEntity<String>(data.toString(), headers);
		restTemplate.postForObject(url + "/notification", request, String.class);
	}

}
