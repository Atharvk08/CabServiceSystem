package com.cabservice.trip_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.trip_service.Model.Trip;
import com.cabservice.trip_service.Service.TripService;

@RestController
public class TripController {

	@Autowired
	private TripService tripService;
	
	@GetMapping("/")
	public Trip getTrip(@RequestParam String tripId, @RequestParam Long userID) {
		return tripService.getTrip(tripId, userID);
		
	}
	@GetMapping("/")
	public List<Trip> getAllTrip(@RequestParam Long userID) {
		return tripService.getAllTrips(userID);
		
	}
	
}
