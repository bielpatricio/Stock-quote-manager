package br.com.inatel.stockquotemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.stockquotemanager.model.Quotes;

public interface QuotesRepository extends JpaRepository<Quotes, Long> {

	List<Quotes> findByStockId(String id);

}
