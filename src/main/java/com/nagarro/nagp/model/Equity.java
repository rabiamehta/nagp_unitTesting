package com.nagarro.nagp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="equity")
public class Equity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int equityId;
	
	@NotNull
	private String equityName;
	
	@NotNull
	private int equitySellingPrice;
	
	@NotNull
	private int quantityIssued;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "etrader_equity")
	private List<TraderProfile> etrader;

	public int getEquityId() {
		return equityId;
	}

	public void setEquityId(int equityId) {
		this.equityId = equityId;
	}

	public String getEquityName() {
		return equityName;
	}

	public void setEquityName(String equityName) {
		this.equityName = equityName;
	}

	public int getEquitySellingPrice() {
		return equitySellingPrice;
	}

	public void setEquitySellingPrice(int equitySellingPrice) {
		this.equitySellingPrice = equitySellingPrice;
	}

	public int getQuantityIssued() {
		return quantityIssued;
	}

	public void setQuantityIssued(int quantityIssued) {
		this.quantityIssued = quantityIssued;
	}

	public List<TraderProfile> getEtrader() {
		return etrader;
	}

	public void setEtrader(List<TraderProfile> etrader) {
		this.etrader = etrader;
	}
}
