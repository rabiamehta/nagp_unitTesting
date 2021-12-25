package com.nagarro.nagp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.nagp.model.Equity;
import com.nagarro.nagp.repository.EquityRepository;
import com.nagarro.nagp.service.imp.EquityServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EquityServiceTest {
	@InjectMocks
    EquityServiceImpl equityService;
     
    @Mock
    EquityRepository equityRepository;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void shouldBeAbleToAddEquity() {
    	Equity eqOne = new Equity(1,"titan",500,10,null);
    	equityService.addEquity(eqOne);
    	verify(equityRepository, times(1)).save(eqOne);
    }
    
    @Test
    public void shouldReturnAllEquities() {
    	List<Equity> list = new ArrayList<Equity>();
    	Equity eqOne = new Equity(1,"titan",500,10,null);
    	Equity eqTwo = new Equity(2,"reliance",200,110,null);
    	Equity eqThree = new Equity(1,"abc",910,6,null);
         
        list.add(eqOne);
        list.add(eqTwo);
        list.add(eqThree);
         
        when(equityRepository.findAll()).thenReturn(list);
        //test
        List<Equity> empList = equityService.getAllEquities();
        assertEquals(3, empList.size());
        verify(equityRepository, times(1)).findAll();
    }
    
    @Test
    public void shouldReturnEquityById() throws Exception {
    	Optional<Equity> eqOne = Optional.of(new Equity(1,"titan",500,10,null));
    	Equity equity=eqOne.get();
    	when(equityRepository.findById(1)).thenReturn(eqOne);
    	// test
    	Equity res = equityService.getEquityById(1);
    	
    	assertEquals(equity, res);
    	assertNotNull(res);
    }
    
}
