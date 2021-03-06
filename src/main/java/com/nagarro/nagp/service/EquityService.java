package com.nagarro.nagp.service;

import java.util.List;

import com.nagarro.nagp.model.Equity;

public interface EquityService {

	Equity addEquity(Equity equity);

	List<Equity> getAllEquities();

	Equity getEquityById(int equityId) throws Exception;	
}
