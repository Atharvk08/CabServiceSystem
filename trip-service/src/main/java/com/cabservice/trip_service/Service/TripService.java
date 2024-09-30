package com.cabservice.trip_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cabservice.trip_service.Model.Trip;
import com.cabservice.trip_service.Model.TripDTO;
import com.cabservice.trip_service.Repository.TripDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class TripService {
	
	@Autowired
	private TripDAO repository;
	
	public Trip getTrip(String tripId)
	{
		return repository.findTripById(tripId);
	}
	
	public List<Trip> getAllTrips(Long userId)
	{
		return repository.findAll();
	}
	
	public String acceptTrip(Trip trip) {
		repository.save(trip);
		return "Successfully added the trip";
	}
	
	public String getTripPrice(String tripId) throws JsonProcessingException
	{
		Trip trip = getTrip(tripId);
		
		Double amount = trip.getAmount();
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode jsonResponse = objectMapper.createObjectNode();
		
		jsonResponse.put("amount", amount);
		
		return objectMapper.writeValueAsString(jsonResponse);
		
	}
	public String updatePrice(String tripId, TripDTO response) {
		
		Trip trip = getTrip(tripId);
		
		trip.setAmount(Double.parseDouble(response.getPayload()));
		
		return "Success";
	}
}
