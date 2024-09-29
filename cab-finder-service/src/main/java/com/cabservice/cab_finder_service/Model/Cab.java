package com.cabservice.cab_finder_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String registrationNo;
	private String carModel;
	private vehicleType carType;
	private Status status;
	
	public enum Status{
		AVAILABLE, IN_A_RIDE, DISCONNECTED;
	}
	
	public enum vehicleType{
		AUTO, CAR, SEDAN, XUV, INTERCITY;
	}
}
