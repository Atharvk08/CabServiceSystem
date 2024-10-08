package com.cabservice.cab_finder_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cabservice.cab_finder_service.Model.RideRequest;

@Service
public class RideNotificationListener implements MessageListener {

	private static final String RIDE_STATUS_KEY = "rideStatus";
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		
		RideRequest rideRequest = (RideRequest) redisTemplate.getValueSerializer().deserialize(message.getBody());
		
//		driver to accept the ride
		String driverId = "17";
	if(!isRideAccepted(rideRequest.getUserId())){	
		if(acceptRide(driverId, rideRequest)) {
			stopNotification(rideRequest);
		}
		
	}else {
			System.out.println("Ride has already been accepted by another driver");
		}
	}

	private boolean isRideAccepted(String userId) {
		// TODO Auto-generated method stub
		String status = (String) redisTemplate.opsForHash().get(RIDE_STATUS_KEY, userId);
		return "ACCEPTED".equals(status);
	}

	private boolean acceptRide(String driverId, RideRequest rideRequest) {
		// TODO Auto-generated method stub
		
		System.out.println(driverId + " accepted the ride for the rider: "+ rideRequest.getUserId());
		
		return true;
	}

	private void stopNotification(RideRequest rideRequest) {
		// TODO Auto-generated method stub
		redisTemplate.convertAndSend("rideAcceptChannel", rideRequest);
	}
	
}
