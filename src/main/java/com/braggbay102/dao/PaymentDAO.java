package com.braggbay102.dao;

import java.util.List;

import com.braggbay102.dao.GenericDAO;
import com.braggbay102.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


