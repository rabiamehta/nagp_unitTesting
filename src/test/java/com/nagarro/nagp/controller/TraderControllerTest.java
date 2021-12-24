package com.nagarro.nagp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.nagp.model.TraderProfile;
import com.nagarro.nagp.service.TraderService;

@SpringBootTest
@AutoConfigureMockMvc
public class TraderControllerTest {

	@InjectMocks
	TraderController traderController;

	@MockBean
	TraderService traderService;

	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddTrader() throws Exception {
		TraderProfile etrader = new TraderProfile(1, "rabia", 5000, null);
		Mockito.when(traderService.addTrader(etrader)).thenReturn(etrader);
		Assertions.assertNotNull(traderController.addTrader(etrader));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/etrader/addTrader")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(etrader)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.traderId").exists());
		;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void shouldBeAbleToAddFunds() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/etrader/addFunds/1/100"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                        .string("Funds Added"))
				;
		
	}

	@Test
	public void shouldBeAbleToBuyEquity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/etrader/1/buyEquity/1"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content()
                .string("Equity Purchased"))
		;
	}

	@Test
	public void shouldBeAbleToSellEquity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/etrader/1/sellEquity/1"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content()
                .string("Equity Sold"))
		;
	}
}
