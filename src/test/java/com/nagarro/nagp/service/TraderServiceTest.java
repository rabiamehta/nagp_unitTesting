package com.nagarro.nagp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.model.TraderProfile;
import com.nagarro.nagp.repository.TraderRepository;
import com.nagarro.nagp.service.imp.EquityServiceImpl;
import com.nagarro.nagp.service.imp.TraderServiceImpl;
import com.nagarro.nagp.utility.TradeExchangeTimeUtil;

@ExtendWith(MockitoExtension.class)
public class TraderServiceTest {
	@InjectMocks
	TraderServiceImpl traderService;

	@InjectMocks
	EquityServiceImpl equityService;

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
		long id = 1;
		traderService.addFunds(id, 1000);
		verify(traderRepository, times(1)).findById(id);
	}

//	@Test
//	public void shouldBeAbleToBuyEquity() throws Exception {
//		try (MockedStatic<TradeExchangeTimeUtil> utilites = Mockito.mockStatic(TradeExchangeTimeUtil.class)) {
//			utilites.when(TradeExchangeTimeUtil::timeAndDayCheck).thenReturn(true);
//			
//			TraderProfile trader = new TraderProfile(1, "Rabia", 120, null);
//			Optional<TraderProfile> traderOptional = Optional.of(trader);
//			Mockito.when(traderRepository.findById((long) 1)).thenReturn(traderOptional);
//			
//			Equity equity = new Equity(4, "abc", 100, 10, null);
//	
//			List<Equity> equities = new ArrayList<Equity>();
//			equities.add(equity);
//			TraderProfile expectedTrader = new TraderProfile(1, "Rabia", 120, equities);
//			Mockito.when(traderRepository.save(trader)).thenReturn(expectedTrader);
//			TraderProfile actualTrader = traderService.buyEquity(1, 4);
//			assertEquals(expectedTrader, actualTrader);
//			
//		}
//	}

	@Test
	public void shouldBeAbleToFindTraderById() throws Exception {
		Optional<TraderProfile> trader = Optional.of(new TraderProfile(1,"rabia",400,null));
		when(traderRepository.findById((long) 1)).thenReturn(trader);
    	
    	// test
    	Optional<TraderProfile> res = Optional.of(traderService.findTraderById(1));
    	
    	assertEquals(trader, res);
    	assertNotNull(res);
	}
	
	@Test
	public void shouldNotBeAbleToFindTraderById() throws Exception {
		when(traderRepository.findById(Mockito.anyLong())).thenReturn(null);
    	
    	// test
    	TraderProfile res = traderService.findTraderById(Mockito.anyLong());
    	
    	assertNull(res);
	}
}
