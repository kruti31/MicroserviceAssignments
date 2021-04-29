package com.example.currencyexchange.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchange.model.CurrencyExchange;
import com.example.currencyexchange.repository.CurrencyExchangeRepository;
import com.example.currencyexchange.service.CurrencyExchangeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);


	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;


	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@CircuitBreaker(name="discountretry")
	public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {

		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		logger.info("\n\n\n currency value {}");
		return currencyExchange;

	}
	
	@PostMapping("/add")
	public ResponseEntity<CurrencyExchange> saveConversionFactor(@RequestBody CurrencyExchange currencyConversion) {
		currencyExchangeService.saveConversionFactor(currencyConversion);
		return ResponseEntity.ok().body(this.currencyExchangeService.saveConversionFactor(currencyConversion));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CurrencyExchange> updateConverdionFactor(@PathVariable long id,
			@RequestBody CurrencyExchange currencyConversion) throws Throwable {
		currencyConversion.setId(id);
		return ResponseEntity.ok().body(currencyExchangeService.updateConversionFactor(currencyConversion, id));

	}


}
