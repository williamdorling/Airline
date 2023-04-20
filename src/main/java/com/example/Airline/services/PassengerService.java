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
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Passenger addNewPassenger(Passenger passenger){
        passengerRepository.save(passenger);
        return passenger;
    }

//    public Passenger addPassengerToFlight(Long id, Long flightId) {
//
////        find flight by flightId
//        Flight flight = flightRepository.findById(flightId).get();
//
////        find passenger by id
//        Passenger passenger = passengerRepository.findById(id).get();
//
////        check flight not at capacity
//        if (flight.getPassengers().size() < flight.getCapacity()){
//
////            add flight to passenger's flights list and save passenger
//            passenger.addFlight(flight);
//            passengerRepository.save(passenger);
//            flightRepository.save(flight);
//
//        } return passenger;
//    }
}
