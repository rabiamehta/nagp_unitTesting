package com.nagarro.nagp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.service.EquityService;

@SpringBootTest
@AutoConfigureMockMvc
public class EqutyControllerTest {

	@InjectMocks
	EquityController equityController;

	@MockBean
	EquityService equityService;

	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Disabled
	@Test
	public void shouldGetAllEquitiesList() throws Exception {
		Equity equity = new Equity(1, "testEquity", 600, 10, null);
		List<Equity> equities = new ArrayList<Equity>();
		equities.add(equity);

		Mockito.when(equityService.getAllEquities()).thenReturn(equities);
		Assertions.assertNotNull(equityController.getAllEquities());

		mockMvc.perform(MockMvcRequestBuilders.get("/equity")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	public void shouldGetAllEquitiesListAsEmpty() throws Exception {
		Mockito.when(equityService.getAllEquities()).thenReturn(null);
		Assertions.assertNull(equityController.getAllEquities());

		mockMvc.perform(MockMvcRequestBuilders.get("/equity")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void shouldGetEquityById() throws Exception {
		Equity equity = new Equity(1, "testEquity", 600, 10, null);
		Mockito.when(equityService.getEquityById(1)).thenReturn(equity);
		Assertions.assertNotNull(equityController.getEquityById(1));

		mockMvc.perform(MockMvcRequestBuilders.get("/equity/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.equityName", Matchers.is("testEquity")));
	}

	@Test
	public void shouldAddEquity() throws Exception {
		Equity equity = new Equity(1, "testEquity", 600, 10, null);
		Mockito.when(equityService.addEquity(equity)).thenReturn(equity);
		Assertions.assertNotNull(equityController.addEquity(equity));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/equity")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(equity))).andExpect(status().isOk())
		;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
