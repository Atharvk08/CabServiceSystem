package com.cabservice.cab_finder_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import com.cabservice.cab_finder_service.Model.RideRequest;

public class RideAcceptanceListener implements MessageListener {


	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final String RIDE_STATUS_KEY = "rideStatus";
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		RideRequest rideRequest = (RideRequest) redisTemplate.getValueSerializer().deserialize(message.getBody());
		
//		mark the ride as accepted 
		markRideAsAccepted(rideRequest.getUserId());
		
		System.out.println("ride accepted by driver, notifying other drivers to stop.");
	}

	// store the ride acceptance status in the redis 
	private void markRideAsAccepted(String userId) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash()
.put(RIDE_STATUS_KEY, userId, "ACCEPTED");
		}

}
