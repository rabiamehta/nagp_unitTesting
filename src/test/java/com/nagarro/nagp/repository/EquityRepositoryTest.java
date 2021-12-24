package com.nagarro.nagp.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nagarro.nagp.model.Equity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EquityRepositoryTest {
	@Autowired
	private EquityRepository equityRepository;

	@Test
	public void testCreateReadDeleteEquity() {

		Equity equity = new Equity(1, "titan", 500, 100, null);
		equityRepository.save(equity);

		Iterable<Equity> equities = equityRepository.findAll();

		Assertions.assertThat(equities).extracting((eq) -> eq.getEquityName()).containsOnly("titan");

		equityRepository.deleteAll();

		Assertions.assertThat(equityRepository.findAll()).isEmpty();
	}
}
