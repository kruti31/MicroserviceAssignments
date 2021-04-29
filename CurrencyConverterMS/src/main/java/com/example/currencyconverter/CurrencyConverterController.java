package com.example.currencyconverter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.currencyconverter.proxy.CurrencyConverterProxyService;


@RestController
public class CurrencyConverterController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);

	@Autowired
	private CurrencyConverterProxyService proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

//		return new CurrencyConverterBean(100L, from,to,BigDecimal.ONE, quantity,quantity.multiply(BigDecimal.valueOf(74.85)));
//
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConverterBean.class,
				uriVariables);
		CurrencyConverterBean currencyConverterBean = responseEntity.getBody();
		logger.info("\n\n\n  currencyConverterBean {}");
		return new CurrencyConverterBean(currencyConverterBean.getId(), currencyConverterBean.getFrom(), currencyConverterBean.getTo(),
				currencyConverterBean.getConversionMultiple(), quantity, quantity.multiply(currencyConverterBean.getConversionMultiple()));
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean retrieveExchangeValueFromFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConverterBean response = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConverterBean(response.getId(), response.getFrom(), response.getTo(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()));
	}

}
