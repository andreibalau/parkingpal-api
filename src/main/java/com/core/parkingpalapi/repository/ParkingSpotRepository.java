package com.core.parkingpalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.parkingpalapi.model.ParkingSpot;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

}
