package com.core.parkingpalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.parkingpalapi.model.ParkingSpot;
import com.core.parkingpalapi.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	private ParkingSpotRepository parkingSpotRepository;

	@Autowired
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}

	public void updateParkingSpotAvailability(ParkingSpot sensorData) {
		parkingSpotRepository.save(sensorData);
	}
}
