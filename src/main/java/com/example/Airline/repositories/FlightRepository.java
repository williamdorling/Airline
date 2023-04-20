package com.example.Airline.repositories;

import com.example.Airline.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestination(String destination);
}
