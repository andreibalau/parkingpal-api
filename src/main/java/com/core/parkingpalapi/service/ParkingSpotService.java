package com.core.parkingpalapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.parkingpalapi.dto.ParkingSpotRequestDto;
import com.core.parkingpalapi.dto.ParkingSpotResponseDto;
import com.core.parkingpalapi.model.ParkingSpot;
import com.core.parkingpalapi.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	private ParkingSpotRepository parkingSpotRepository;

	@Autowired
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}

	public void updateParkingSpotAvailability(ParkingSpotRequestDto sensorData) {
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

	public List<ParkingSpotResponseDto> getAllAvailableSpots() {
		return parkingSpotRepository.findAll().stream().filter(spot -> spot.getAvailability().equals(true))
				.map(spot -> new ParkingSpotResponseDto(spot.getLatitude(), spot.getLongitude()))
				.collect(Collectors.toList());
	}
}
