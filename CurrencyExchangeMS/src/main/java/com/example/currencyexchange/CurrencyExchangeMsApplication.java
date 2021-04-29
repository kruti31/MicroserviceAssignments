package com.example.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyExchangeMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeMsApplication.class, args);
		
		
	}

}
