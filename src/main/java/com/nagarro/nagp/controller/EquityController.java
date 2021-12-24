package com.nagarro.nagp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.service.EquityService;

@RestController
@RequestMapping("/equity")
public class EquityController {

	@Autowired
	EquityService equityService;

	@GetMapping()
	public Iterable<Equity> getAllEquities() {
		return equityService.getAllEquities();
	}

	@GetMapping("{equityId}")
	public Equity getEquityById(@PathVariable int equityId) throws Exception {
		return equityService.getEquityById(equityId);
	}

	@PostMapping
	public Equity addEquity(@RequestBody Equity equity) {
		return equityService.addEquity(equity);
	}

}
