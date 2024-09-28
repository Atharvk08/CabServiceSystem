package com.cabservice.location_service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("Location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoCoordinate {
	@Id
	private Long userId;	
	private Double latitude;
	private Double longitude;
	private String geohash;
	private String role;
}
