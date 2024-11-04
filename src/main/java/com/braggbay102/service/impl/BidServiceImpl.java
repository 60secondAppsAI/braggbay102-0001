package com.braggbay102.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay102.dao.GenericDAO;
import com.braggbay102.service.GenericService;
import com.braggbay102.service.impl.GenericServiceImpl;
import com.braggbay102.dao.BidDAO;
import com.braggbay102.domain.Bid;
import com.braggbay102.dto.BidDTO;
import com.braggbay102.dto.BidSearchDTO;
import com.braggbay102.dto.BidPageDTO;
import com.braggbay102.dto.BidConvertCriteriaDTO;
import com.braggbay102.dto.common.RequestDTO;
import com.braggbay102.dto.common.ResultDTO;
import com.braggbay102.service.BidService;
import com.braggbay102.util.ControllerUtils;





@Service
public class BidServiceImpl extends GenericServiceImpl<Bid, Integer> implements BidService {

    private final static Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);

	@Autowired
	BidDAO bidDao;

	


	@Override
	public GenericDAO<Bid, Integer> getDAO() {
		return (GenericDAO<Bid, Integer>) bidDao;
	}
	
	public List<Bid> findAll () {
		List<Bid> bids = bidDao.findAll();
		
		return bids;	
		
	}

	public ResultDTO addBid(BidDTO bidDTO, RequestDTO requestDTO) {

		Bid bid = new Bid();

		bid.setBidId(bidDTO.getBidId());


		bid.setAmount(bidDTO.getAmount());


		bid.setBidTime(bidDTO.getBidTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		bid = bidDao.save(bid);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Bid> getAllBids(Pageable pageable) {
		return bidDao.findAll(pageable);
	}

	public Page<Bid> getAllBids(Specification<Bid> spec, Pageable pageable) {
		return bidDao.findAll(spec, pageable);
	}

	public ResponseEntity<BidPageDTO> getBids(BidSearchDTO bidSearchDTO) {
	
			Integer bidId = bidSearchDTO.getBidId(); 
    			String sortBy = bidSearchDTO.getSortBy();
			String sortOrder = bidSearchDTO.getSortOrder();
			String searchQuery = bidSearchDTO.getSearchQuery();
			Integer page = bidSearchDTO.getPage();
			Integer size = bidSearchDTO.getSize();

	        Specification<Bid> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, bidId, "bidId"); 
			
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Bid> bids = this.getAllBids(spec, pageable);
		
		//System.out.println(String.valueOf(bids.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(bids.getTotalPages()));
		
		List<Bid> bidsList = bids.getContent();
		
		BidConvertCriteriaDTO convertCriteria = new BidConvertCriteriaDTO();
		List<BidDTO> bidDTOs = this.convertBidsToBidDTOs(bidsList,convertCriteria);
		
		BidPageDTO bidPageDTO = new BidPageDTO();
		bidPageDTO.setBids(bidDTOs);
		bidPageDTO.setTotalElements(bids.getTotalElements());
		return ResponseEntity.ok(bidPageDTO);
	}

	public List<BidDTO> convertBidsToBidDTOs(List<Bid> bids, BidConvertCriteriaDTO convertCriteria) {
		
		List<BidDTO> bidDTOs = new ArrayList<BidDTO>();
		
		for (Bid bid : bids) {
			bidDTOs.add(convertBidToBidDTO(bid,convertCriteria));
		}
		
		return bidDTOs;

	}
	
	public BidDTO convertBidToBidDTO(Bid bid, BidConvertCriteriaDTO convertCriteria) {
		
		BidDTO bidDTO = new BidDTO();
		
		bidDTO.setBidId(bid.getBidId());

	
		bidDTO.setAmount(bid.getAmount());

	
		bidDTO.setBidTime(bid.getBidTime());

	

		
		return bidDTO;
	}

	public ResultDTO updateBid(BidDTO bidDTO, RequestDTO requestDTO) {
		
		Bid bid = bidDao.getById(bidDTO.getBidId());

		bid.setBidId(ControllerUtils.setValue(bid.getBidId(), bidDTO.getBidId()));

		bid.setAmount(ControllerUtils.setValue(bid.getAmount(), bidDTO.getAmount()));

		bid.setBidTime(ControllerUtils.setValue(bid.getBidTime(), bidDTO.getBidTime()));



        bid = bidDao.save(bid);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BidDTO getBidDTOById(Integer bidId) {
	
		Bid bid = bidDao.getById(bidId);
			
		
		BidConvertCriteriaDTO convertCriteria = new BidConvertCriteriaDTO();
		return(this.convertBidToBidDTO(bid,convertCriteria));
	}







}
