package com.nagarro.nagp.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class TraderExchangeTimeUtilTest {
	
	@Test
	public void testisWeekday() {
		assertEquals(false, TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 25)));
		try(MockedStatic<TradeExchangeTimeUtil> theMock = Mockito.mockStatic(TradeExchangeTimeUtil.class)) {
			theMock.when(() -> TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 25))).thenReturn(false);
			assertEquals(false, TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 25)));
		}
		
		assertEquals(true, TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 24)));
		try(MockedStatic<TradeExchangeTimeUtil> theMock = Mockito.mockStatic(TradeExchangeTimeUtil.class)) {
			theMock.when(() -> TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 24))).thenReturn(true);
			assertEquals(true, TradeExchangeTimeUtil.isWeekday(LocalDate.of(2021, 12, 24)));
		}
	}
	
	@Test
	public void timeAndDayCheck() {
		assertEquals(false, TradeExchangeTimeUtil.timeAndDayCheck());
		try(MockedStatic<TradeExchangeTimeUtil> theMock = Mockito.mockStatic(TradeExchangeTimeUtil.class)){
			theMock.when(() -> TradeExchangeTimeUtil.timeAndDayCheck()).thenReturn(false);
			assertEquals(false, TradeExchangeTimeUtil.timeAndDayCheck());
		}
	}
	
}
	