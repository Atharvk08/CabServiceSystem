package com.cabservice.cab_finder_service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.cab_finder_service.Model.Cab;
import com.cabservice.cab_finder_service.Service.CabService;

@RestController
@RequestMapping("/api/v1")
public class CabController {


	@Autowired
	CabService cabService;
	
	@GetMapping("/cab/")
	public List<Cab> getAllCabs() {
		return cabService.getAllCab();
	}
	
	@GetMapping("/cab/{id}")
	public Optional<Cab> getCabById(@PathVariable Long id) {
		return cabService.getCab(id);
	}
	
	@PostMapping("/cab/")
	public String createCab(@RequestBody Cab cab) {
		return cabService.addCab(cab);
	}
	@PutMapping("/cab/")
	public String updateCab(@RequestBody Cab cab) {
		return cabService.updateCab(cab);
	}
	@DeleteMapping("/cab/{id}")
	public String createCab(@PathVariable Long id) {
		return cabService.deleteCab(id);
	}
}
