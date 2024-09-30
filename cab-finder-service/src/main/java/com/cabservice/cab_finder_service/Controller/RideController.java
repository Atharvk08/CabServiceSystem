package com.cabservice.cab_finder_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.cab_finder_service.Model.RideRequest;
import com.cabservice.cab_finder_service.Service.CabService;

@RestController
@RequestMapping("/api/v1/ride")
public class RideController {
	
	@Autowired
	private CabService cabService;
	
	@PostMapping("/book")		
	public String bookRide(@RequestBody RideRequest rideRequest) {
		//TODO: process POST request
		
		return cabService.bookRide(rideRequest);
	}
	
	public String endRide() {
		
		return "";
	}
	
}
