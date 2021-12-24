package com.nagarro.nagp.service;

import com.nagarro.nagp.model.TraderProfile;

public interface TraderService {

	void addFunds(long traderId, int fundAmount);
	TraderProfile addTrader(TraderProfile etrader);
	void buyEquity(long traderId, int equityId) throws Exception;
	void sellEquity(long traderId, int equityId) throws Exception;
}
