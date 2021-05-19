package br.com.inatel.stockquotemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quotes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockId;
	private BigDecimal price;
	private LocalDate timeNow = LocalDate.now();

	public Quotes(String stockId, LocalDate timeNow, BigDecimal price) {
		this.price = price;
		this.timeNow = timeNow;
		this.stockId = stockId;
	}

	public Quotes() {

	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getTimeNow() {
		return timeNow;
	}

	public void setTimeNow(LocalDate timeNow) {
		this.timeNow = timeNow;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

}
