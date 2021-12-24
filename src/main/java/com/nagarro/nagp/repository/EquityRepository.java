package com.nagarro.nagp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.nagp.model.Equity;

@Repository
public interface EquityRepository extends JpaRepository<Equity, Integer>{

}
