package com.core.parkingpalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.parkingpalapi.model.ParkingSpot;
import com.core.parkingpalapi.service.ParkingSpotService;

@RestController
public class ParkingSpotController {

	private ParkingSpotService parkingSpotService;

	@Autowired
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

	@PostMapping(value = "/availability")
	public void updateParkingSpotAvailability(@RequestBody ParkingSpot sensorData) {
		parkingSpotService.updateParkingSpotAvailability(sensorData);
	}
}
