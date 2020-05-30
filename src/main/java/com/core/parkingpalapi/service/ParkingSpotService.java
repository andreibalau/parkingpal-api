package com.core.parkingpalapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.parkingpalapi.dto.ParkingSpotDto;
import com.core.parkingpalapi.model.ParkingSpot;
import com.core.parkingpalapi.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	private ParkingSpotRepository parkingSpotRepository;

	@Autowired
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}

	public void updateParkingSpotAvailability(ParkingSpotDto sensorData) {
		ParkingSpot parkingSpot = parkingSpotRepository.findByLatitudeAndLongitude(sensorData.getLatitude(),
				sensorData.getLongitude());
		if (parkingSpot != null) {
			parkingSpot.setAvailability(sensorData.getAvailability());
			parkingSpotRepository.save(parkingSpot);
		} else {
			parkingSpot = new ParkingSpot();
			parkingSpot.setLatitude(sensorData.getLatitude());
			parkingSpot.setLongitude(sensorData.getLongitude());
			parkingSpot.setAvailability(sensorData.getAvailability());
			parkingSpotRepository.save(parkingSpot);
		}

	}

	public List<ParkingSpotDto> getAllAvailableSpots() {
		List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
		return parkingSpots.stream()
				.map(spot -> new ParkingSpotDto(spot.getLatitude(), spot.getLongitude(), spot.getAvailability()))
				.collect(Collectors.toList());
	}
}
