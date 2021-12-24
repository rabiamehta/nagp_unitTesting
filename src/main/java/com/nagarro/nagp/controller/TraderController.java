package com.nagarro.nagp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.model.TraderProfile;
import com.nagarro.nagp.service.TraderService;

@RestController
@RequestMapping("/etrader")
public class TraderController {

	@Autowired
	TraderService traderService;

	@PostMapping("/addTrader")
	public TraderProfile addTrader(@RequestBody TraderProfile etrader) {
		return traderService.addTrader(etrader);
	}

	@PutMapping("/addFunds/{traderId}/{fundAmount}")
	public String addFunds(@PathVariable("traderId") long traderId, @PathVariable("fundAmount") int fundAmount) {
		traderService.addFunds(traderId, fundAmount);
		return "Funds Added";
	}

	@PutMapping("{traderId}/buyEquity/{equityId}")
	public String buyEquity(@PathVariable long traderId, @PathVariable int equityId) throws Exception {
		traderService.buyEquity(traderId, equityId);
		return "Equity Purchased";
	}

	@PutMapping("{traderId}/sellEquity/{equityId}")
	public String sellEquity(@PathVariable long traderId, @PathVariable int equityId) throws Exception {
		traderService.sellEquity(traderId, equityId);
		return "Equity Sold";
	}
}
