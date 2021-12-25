package com.nagarro.nagp.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.nagp.model.TraderProfile;
import com.nagarro.nagp.repository.TraderRepository;
import com.nagarro.nagp.service.imp.TraderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TraderServiceTest {
	@InjectMocks
	TraderServiceImpl traderService;

	@Mock
	TraderRepository traderRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldBeAbleToAddTrader() {
		TraderProfile trader = new TraderProfile(1, "rabia", 5000, null);
		traderService.addTrader(trader);
		verify(traderRepository, times(1)).save(trader);
	}
	
	@Test
	public void shouldBeAbleToAddFunds() {
		long id =1;
		traderService.addFunds(id, 1000);
		verify(traderRepository, times(1)).findById(id);
	}
	
	@Test
	public void shouldeAbleToBuyEquity() {
		
	}
}
