package com.cabservice.trip_service.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cabservice.trip_service.Model.Trip;

@Repository
public class TripDAO {

	public static final String HASH_KEY_TRIP = "trip";
	
	@Autowired
	private RedisTemplate template;
	
	public Trip save(Trip trip)
	{
		template.opsForHash().put(HASH_KEY_TRIP, trip.getId(), trip);
		return trip;
	}
	
	public Trip findTripById(String tripId) {
		return (Trip) template.opsForHash().get(HASH_KEY_TRIP, tripId);
	}
	
//	public Trip findTripByUserId(String userId) {
//		if(template.opsForHash().)
//	}
	
	public List<Trip> findAll() {
		return template.opsForHash().values(HASH_KEY_TRIP);
	}

	public String deleteTrip(String id) {
		template.opsForHash().delete(HASH_KEY_TRIP, id);
		return "trip removed!";
	}

}
