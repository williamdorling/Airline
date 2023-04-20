package com.example.Airline.services;

import com.example.Airline.models.Flight;
import com.example.Airline.models.Passenger;
import com.example.Airline.repositories.FlightRepository;
import com.example.Airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight addNewFlight(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    public void deleteFlightById(Long id) {
        Flight flight =flightRepository.findById(id).get();
        for (Passenger passenger : flight.getPassengers()){
            passenger.removeFlight(flight);
            flightRepository.save(flight);
        }
        flightRepository.deleteById(id);
    }

    public List<Flight> findAllFlightsByDestination(String destination) {
        return flightRepository.findByDestination(destination);
    }

    public Flight addPassengerToFlight(Long id, Long passengerId) {

//        find flight by id
        Flight flight = flightRepository.findById(id).get();

//        find passenger by PassengerId
        Passenger passenger = passengerRepository.findById(passengerId).get();

//        add flight to passenger's flights list and save passenger
        flight.addPassenger(passenger);
        flightRepository.save(flight);
        passengerRepository.save(passenger);

         return flight;

    }
}
