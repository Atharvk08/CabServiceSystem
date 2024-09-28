package com.cabservice.cab_finder_service.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.cab_finder_service.Model.Cab;
import com.cabservice.cab_finder_service.Repostitory.CabRepository;

@Service
public class CabService {
	
	@Autowired
	private CabRepository cabRepo;
	
	public List<Cab> findAllCabsNearby(Double radius)
	{
		List<Cab> result = new LinkedList<>();
		
		
		
		return result;
	}
	
	
	
	
	public String addCab(Cab cab)
	{
		cabRepo.save(cab);
		return "success";
	}
	public String deleteCab(Long cabId)
	{
		cabRepo.deleteById(cabId);
		return "success";
	}
	public String updateCab(Cab cab)
	{
		cabRepo.save(cab);
		return "success";
	}
	public Optional<Cab> getCab(Long cabId)
	{
		Optional<Cab> cab = cabRepo.findById(cabId);
		return cab;
	}
	public List<Cab> getAllCab()
	{
		return cabRepo.findAll();
	}
	
	
	
}
