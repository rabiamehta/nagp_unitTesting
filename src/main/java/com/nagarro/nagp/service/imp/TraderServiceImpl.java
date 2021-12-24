package com.nagarro.nagp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.model.TraderProfile;
import com.nagarro.nagp.repository.TraderRepository;
import com.nagarro.nagp.service.EquityService;
import com.nagarro.nagp.service.TraderService;
import com.nagarro.nagp.utility.TradeExchangeTimeUtil;

@Service
public class TraderServiceImpl implements TraderService {

	@Autowired
	EquityService equityService;

	@Autowired
	TraderRepository traderRepository;

	@Override
	public void addFunds(long traderId, int fundAmount) {
		traderRepository.findById(traderId).ifPresent((trader) -> {
			trader.setAvailableBalance(fundAmount + trader.getAvailableBalance());
			traderRepository.save(trader);
		});
	}

	@Override
	public TraderProfile addTrader(TraderProfile etrader) {
		return traderRepository.save(etrader);
	}

	private TraderProfile findTraderById(long traderId) throws Exception {
		Optional<TraderProfile> eTrader = traderRepository.findById(traderId);
		if (eTrader.isPresent()) {
			return eTrader.get();
		} else {
			throw new Exception("Trader not found");
		}
	}

	public void buyEquity(long traderId, int equityId) throws Exception {
		if (echangeTimechk()) {
			Equity equity = equityService.getEquityById(equityId);
			TraderProfile trader = findTraderById(traderId);

			int tradingAvailableBalance = trader.getAvailableBalance();
			int holdingsAmt = equity.getEquitySellingPrice();

			if (tradingAvailableBalance >= holdingsAmt) {
				List<Equity> holdings = trader.getEquities();
				holdings.add(equity);
				trader.setEquities(holdings);
				trader.setAvailableBalance(tradingAvailableBalance - holdingsAmt);
				traderRepository.save(trader);

				equity.setQuantityIssued(equity.getQuantityIssued() - 1);
				List<TraderProfile> traders = equity.getEtrader();
				traders.add(trader);
				equity.setEtrader(traders);
				equityService.addEquity(equity);
			} else {
				throw new Exception("Appropriate Funds are not available for this purchase!");
			}

		} else {
			throw new Exception("Exchange Time over !");
		}
	}

	public void sellEquity(long traderId, int equityId) throws Exception {
		if (echangeTimechk()) {
			TraderProfile trader = findTraderById(traderId);
			List<Equity> allTraderHoldings = trader.getEquities();

			allTraderHoldings.forEach(holding -> {
				if (holding.getEquityId() == equityId) {
					trader.setAvailableBalance(trader.getAvailableBalance() + holding.getEquitySellingPrice());
				}
			});
		} else {
			throw new Exception("Exchange Time over !");
		}
	}

	private static boolean echangeTimechk() {
		return TradeExchangeTimeUtil.timeAndDayCheck();
	}

}
