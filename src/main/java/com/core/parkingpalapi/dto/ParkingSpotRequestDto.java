package com.core.parkingpalapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpotRequestDto {
	private Double latitude;
	private Double longitude;
	private Boolean availability;
}
