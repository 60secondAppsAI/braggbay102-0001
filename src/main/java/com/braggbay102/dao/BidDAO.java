package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


