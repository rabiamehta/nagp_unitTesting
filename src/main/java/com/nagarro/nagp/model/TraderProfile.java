package com.nagarro.nagp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "etrader")
@AllArgsConstructor
@NoArgsConstructor
public class TraderProfile {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long traderId;

	@NotNull
	private String traderName;

	@NotNull
	private int availableBalance;
	
	@ManyToMany(targetEntity = Equity.class, mappedBy = "etrader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Equity> equities;

	public long getTraderId() {
		return traderId;
	}

	public void setTraderId(long traderId) {
		this.traderId = traderId;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public int getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}

	public List<Equity> getEquities() {
		return equities;
	}

	public void setEquities(List<Equity> equities) {
		this.equities = equities;
	}
	
}
