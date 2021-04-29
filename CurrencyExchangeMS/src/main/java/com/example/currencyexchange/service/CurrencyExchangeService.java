package com.example.currencyexchange.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.currencyexchange.model.CurrencyExchange;
import com.example.currencyexchange.repository.CurrencyExchangeRepository;

import javassist.NotFoundException;


@Service
public class CurrencyExchangeService {
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;

	
	public CurrencyExchange saveConversionFactor(CurrencyExchange currencyExchange) {
		return currencyExchangeRepository.save(currencyExchange);
	}

	public CurrencyExchange updateConversionFactor(CurrencyExchange currencyExchange, long id)
			throws Throwable {

		Optional<CurrencyExchange> currencyDb = currencyExchangeRepository.findById(currencyExchange.getId());

		if (currencyDb.isPresent()) {
			CurrencyExchange currencyUpdate = currencyDb.get();
			currencyUpdate.setId(currencyExchange.getId());
			currencyUpdate.setFrom(currencyExchange.getFrom());
			currencyUpdate.setTo(currencyExchange.getTo());
			currencyUpdate.setConversionMultiple(currencyExchange.getConversionMultiple());
			currencyExchangeRepository.save(currencyUpdate);
			return currencyUpdate;
		} else {
			throw new NotFoundException("Record not found with id : " + currencyExchange.getId());
		}
	}


}
