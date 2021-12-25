package com.nagarro.nagp.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class TradeExchangeTimeUtil {
	private TradeExchangeTimeUtil() {}
	
	public static boolean timeAndDayCheck() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		return isWeekday(currentDate) && (currentTime.isAfter(LocalTime.parse("09:00:00"))
				&& currentTime.isBefore(LocalTime.parse("17:00:00")));
	}

	public static boolean isWeekday(final LocalDate localDate) {
		DayOfWeek currentDay = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
		boolean weekend = (currentDay == DayOfWeek.SATURDAY || currentDay == DayOfWeek.SUNDAY);
		return !weekend;
	}
}
