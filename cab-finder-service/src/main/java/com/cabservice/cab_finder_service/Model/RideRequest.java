package com.cabservice.cab_finder_service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequest {

	private String userId;
	private double sourceLat;
	private double sourceLon;
	private double destLat;
	private double destLon;
}
