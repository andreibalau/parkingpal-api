package com.core.parkingpalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.parkingpalapi.model.ParkingSpot;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

	/**
	 * @param latitude
	 * @param longitude
	 * @return ParkingSpot object if any with the given values
	 */
	ParkingSpot findByLatitudeAndLongitude(Double latitude, Double longitude);
}
