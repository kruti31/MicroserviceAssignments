package com.example.currencyexchange.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.currencyexchange.model.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	
	
	CurrencyExchange findByFromAndTo(String from,String to);
	
//	CurrencyExchange addConversionFactor(String from, String to, BigDecimal conversionMultiple);

}
