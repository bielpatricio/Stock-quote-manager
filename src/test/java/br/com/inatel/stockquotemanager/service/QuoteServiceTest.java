package br.com.inatel.stockquotemanager.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.stockquotemanager.model.Quotes;
import br.com.inatel.stockquotemanager.repository.QuotesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuoteServiceTest {

	private List<Quotes> quoteList;

	@MockBean
	private QuotesRepository quotesRepository;

	@InjectMocks
	private QuoteService quoteService;

	@Before
	public void before() {
		quoteService = new QuoteService(quotesRepository);
	}

	@Test
	public void findByStockIdTest() throws Exception {

		quoteList = new ArrayList<>();
		String id = "vale5";
		Mockito.when(quotesRepository.findByStockId(id)).thenReturn(quoteList);
		Assert.assertEquals(quoteList.size(), quoteService.findByStockId(id).size());
	}

}
