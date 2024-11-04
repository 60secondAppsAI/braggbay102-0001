package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.Item;





public interface ItemDAO extends GenericDAO<Item, Integer> {
  
	List<Item> findAll();
	






}


