package com.nagarro.nagp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.repository.EquityRepository;
import com.nagarro.nagp.service.EquityService;

@Service
public class EquityServiceImpl implements EquityService {

	@Autowired
	EquityRepository equityRepository;

	@Override
	public Equity addEquity(Equity equity) {
		return equityRepository.save(equity);
	}

	@Override
	public List<Equity> getAllEquities() {
		return equityRepository.findAll();
	}

	@Override
	public Equity getEquityById(int equityId) throws Exception {
		Optional<Equity> equity = equityRepository.findById(equityId);
		if (equity.isPresent()) {
			return equity.get();
		} else {
			throw new Exception("Equity not found");
		}
	}
}
