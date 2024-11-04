package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


