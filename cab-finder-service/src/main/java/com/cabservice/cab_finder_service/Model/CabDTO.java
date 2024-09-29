package com.cabservice.cab_finder_service.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabDTO {

	@Id
	private String driverId;
	private Double latitude;
	private Double longitude;
//	add status string var
	
}
