package br.com.inatel.stockquotemanager.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.stockquotemanager.model.Quotes;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class QuoesRepositoryTest {

	@Autowired
	private QuotesRepository quoesRepository;

	@Test
	void findByStockIdTest() {

		Quotes quoes = quoesRepository.getOne((long) 1);
		Assert.assertNotNull(quoes);
	}

}
