package com.cabservice.trip_service.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Trip {
//	@Id
	private Long id;
	private Long userId;
	private Long driverId;
	private Status status;
	private Double start_latitude;
	private Double end_latitude;
	private Double start_longitude;
	private Double end_longitude;
	private List<String> path;
	private Long paymentId;
	
	public enum Status{
		NOT_STARTED, IN_PROGRESS, ENDED
	}
}
