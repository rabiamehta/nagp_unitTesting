package com.nagarro.nagp.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nagarro.nagp.model.TraderProfile;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TraderRepositoryTest {
	@Autowired
	private TraderRepository traderRepository;

	@Test
	public void testCreateReadDeleteEquity() {

		TraderProfile trader = new TraderProfile(1, "rabia", 5000, null);
		traderRepository.save(trader);

		Iterable<TraderProfile> traders = traderRepository.findAll();

		Assertions.assertThat(traders).extracting((tr) -> tr.getTraderName()).containsOnly("rabia");

		traderRepository.deleteAll();

		Assertions.assertThat(traderRepository.findAll()).isEmpty();
	}
}
