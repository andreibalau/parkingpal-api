package com.core.parkingpalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParkingSpotDto {
	private Double latitude;
	private Double longitude;
	private Boolean availability;
}
