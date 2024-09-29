package com.cabservice.location_service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

	private Long userId;	
	private Double latitude;
	private Double longitude;
}
