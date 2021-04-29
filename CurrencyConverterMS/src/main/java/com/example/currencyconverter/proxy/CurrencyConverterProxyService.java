package com.example.currencyconverter.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.currencyconverter.CurrencyConverterBean;

@FeignClient(name="currency-exchange-ms", url="http://localhost:8000/")
public interface CurrencyConverterProxyService {

	 @GetMapping("/currency-exchange/from/{from}/to/{to}")
	   public CurrencyConverterBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
