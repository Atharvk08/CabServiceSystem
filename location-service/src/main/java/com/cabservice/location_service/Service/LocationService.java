package com.cabservice.location_service.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cabservice.location_service.Model.GeoCoordinate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class LocationService {

	private final static String LOCATION_KEY = "locations";
	private static final String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
	private static final int PRECISION = 12; // You can adjust this precision
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate; 
	
	public void saveLocation(Long userId, Double latitude, Double longitude) {
		
		String geohash = encodeGeohash(latitude, longitude);
		String role = "DRIVER";
		GeoCoordinate location = new GeoCoordinate(userId, latitude, longitude, geohash,role );
		
		// save location to redis db
		redisTemplate.opsForValue().set(userId.toString(), location);
		
		// save geospatial data
		GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
		geoOps.add(LOCATION_KEY, new Point( longitude ,latitude),userId);
		
	}
	
	public GeoCoordinate getLocation(Long userId) {
		return (GeoCoordinate) redisTemplate.opsForValue().get(userId.toString());
	}
	public GeoCoordinate getLocation(String userId) {
		return (GeoCoordinate) redisTemplate.opsForValue().get(userId);
	}
	
	public String getNearByLocations(Double latitude, Double longitude, Double radius){
		GeoOperations<String, Object> geoOps = redisTemplate.opsForGeo();
		Circle circle = new Circle(new Point(latitude, longitude), radius);
		List<Object> nearByUserIds = geoOps.radius(LOCATION_KEY, circle)
				.getContent()
				.stream().map(geoResult -> geoResult.getContent()).collect(Collectors.toList());
	
		List<GeoCoordinate> locations = new LinkedList<GeoCoordinate>();
		
		for(Object userId : nearByUserIds) {
			GeoCoordinate currentLoc= getLocation((String)userId );
			if(currentLoc.getRole() == "DRIVER")
				locations.add(currentLoc);
		}
//		convert to JsonObject string
		return convertToJSONString(locations);
		
	}
	
	
    private String convertToJSONString(List<GeoCoordinate> locations) {
		// TODO Auto-generated method stub
		
    	ObjectMapper objMapper = new ObjectMapper();
    	String jsonString ="";
    	
    	try {
			objMapper.writeValueAsString(locations);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;

	}

	private String encodeGeohash(double latitude, double longitude) {
        // Implement Geohash encoding logic or use a library


    	double[] latRange = { -90.0, 90.0 };
        double[] lonRange = { -180.0, 180.0 };

        StringBuilder geohash = new StringBuilder();
        boolean isEvenBit = true;
        int bit = 0, ch = 0;

        while (geohash.length() < PRECISION) {
            double mid;
            if (isEvenBit) {
                // Even bit: divide longitude range
                mid = (lonRange[0] + lonRange[1]) / 2;
                if (longitude >= mid) {
                    ch |= (1 << (4 - bit)); // Set bit to 1
                    lonRange[0] = mid; // Narrow the lower bound
                } else {
                    lonRange[1] = mid; // Narrow the upper bound
                }
            } else {
                // Odd bit: divide latitude range
                mid = (latRange[0] + latRange[1]) / 2;
                if (latitude >= mid) {
                    ch |= (1 << (4 - bit)); // Set bit to 1
                    latRange[0] = mid; // Narrow the lower bound
                } else {
                    latRange[1] = mid; // Narrow the upper bound
                }
            }

            isEvenBit = !isEvenBit;

            if (bit < 4) {
                bit++;
            } else {
                // 5 bits processed, append the character to geohash
                geohash.append(BASE32.charAt(ch));
                bit = 0;
                ch = 0;
            }
        }

        return geohash.toString();
    }
}
