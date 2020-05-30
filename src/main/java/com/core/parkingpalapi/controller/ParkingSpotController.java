package com.core.parkingpalapi.controller;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.parkingpalapi.dto.ParkingSpotDto;
import com.core.parkingpalapi.service.ParkingSpotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@RestController
public class ParkingSpotController {

	private ParkingSpotService parkingSpotService;

	@Autowired
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

	@GetMapping("/sse-news")
	public Flux<ServerSentEvent<String>> streamEvents() {
		return Flux.interval(Duration.ofSeconds(1)).map(sequence -> {
			try {
				return ServerSentEvent.<String>builder().id(String.valueOf(sequence)).event("periodic-event")
						.data(new ObjectMapper().writeValueAsString(parkingSpotService.getAllAvailableSpots())).build();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return ServerSentEvent.<String>builder(Collections.emptyList().toString()).build();
			}
		});
	}

	@GetMapping(value = "/available-spots", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParkingSpotDto> getAllAvailableSpots() {
		return parkingSpotService.getAllAvailableSpots();
	}

	@PostMapping(value = "/availability", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateParkingSpotAvailability(@RequestBody ParkingSpotDto sensorData) {
		parkingSpotService.updateParkingSpotAvailability(sensorData);
	}
}
