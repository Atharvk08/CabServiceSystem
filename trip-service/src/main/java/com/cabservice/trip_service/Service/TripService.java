package com.cabservice.trip_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.trip_service.Model.Trip;
import com.cabservice.trip_service.Repository.TripDAO;

@Service
public class TripService {
	
	@Autowired
	private TripDAO repository;
	
	public Trip getTrip(String tripId, Long userId)
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
	
}
