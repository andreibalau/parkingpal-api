package com.core.parkingpalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.parkingpalapi.dto.ParkingSpotRequestDto;
import com.core.parkingpalapi.dto.ParkingSpotResponseDto;
import com.core.parkingpalapi.service.ParkingSpotService;

@RestController
public class ParkingSpotController {

	private ParkingSpotService parkingSpotService;

	@Autowired
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

	@GetMapping(value = "available-spots", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParkingSpotResponseDto> getAllAvailableSpots() {
		return parkingSpotService.getAllAvailableSpots();
	}

	@PostMapping(value = "/availability", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateParkingSpotAvailability(@RequestBody ParkingSpotRequestDto sensorData) {
		parkingSpotService.updateParkingSpotAvailability(sensorData);
	}
}
