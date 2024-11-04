package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


