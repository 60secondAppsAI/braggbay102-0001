package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.Auction;





public interface AuctionDAO extends GenericDAO<Auction, Integer> {
  
	List<Auction> findAll();
	






}


