package com.cabservice.trip_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.trip_service.Model.Trip;
import com.cabservice.trip_service.Model.TripDTO;
import com.cabservice.trip_service.Service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.ws.rs.POST;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/trip")
public class TripController {

	@Autowired
	private TripService tripService;
	
	@GetMapping("/get-trip")
	public Trip getTrip(@RequestParam String tripId, @RequestParam Long userID) {
		return tripService.getTrip(tripId);
		
	}
	@GetMapping("/{userID}")
	public List<Trip> getAllTrip(@PathVariable Long userID) {
		return tripService.getAllTrips(userID);
		
	}
	
	@PutMapping("/{userId}")
	public String updatePrice(@PathVariable Long userId, @RequestParam String tripId, @RequestBody TripDTO response) {
		//TODO: process PUT request
		
		return tripService.updatePrice(tripId, response);
	}
	@GetMapping("/get-trip/{tripId}")
	public String getTripAmount(@PathVariable String tripId) throws JsonProcessingException {
		return tripService.getTripPrice(tripId);		
	}
	
	
	@PostMapping("/")			
	public String acceptTrip(@RequestParam Long userId ,@RequestBody Trip trip) {
		//TODO: process POST request
		return tripService.acceptTrip(trip);
	}
	
	
}
