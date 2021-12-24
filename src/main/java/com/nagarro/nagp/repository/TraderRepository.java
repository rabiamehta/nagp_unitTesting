package com.nagarro.nagp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.nagp.model.TraderProfile;

@Repository
public interface TraderRepository extends JpaRepository<TraderProfile, Long>{
	
}
