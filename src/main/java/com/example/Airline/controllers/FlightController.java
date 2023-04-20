package com.example.Airline.controllers;

import com.example.Airline.models.Flight;
import com.example.Airline.models.Passenger;
import com.example.Airline.repositories.FlightRepository;
import com.example.Airline.services.FlightService;
import com.example.Airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlightsAndFilters(
            @RequestParam(required = false, name = "destination") String destination
            ){
        if(destination == null){
            return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);

        }else if(flightService.findAllFlightsByDestination(destination).isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(flightService.findAllFlightsByDestination(destination), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id){
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()){
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight){
        Flight newFlight = flightService.addNewFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id){
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()){
            flightService.deleteFlightById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(
            @PathVariable Long id, // flight id
            @RequestParam Long passengerId
    ){
        Optional<Flight> flight = flightService.getFlightById(id);
        Optional<Passenger> passenger = passengerService.getPassengerById(passengerId);
        if ((!flight.isPresent()) || (!passenger.isPresent())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else if(flight.get().getPassengers().size() >= flight.get().getCapacity()){
            return new ResponseEntity<>(flight.get(), HttpStatus.NOT_ACCEPTABLE);
        }else {
            return new ResponseEntity<>(flightService.addPassengerToFlight(id, passengerId), HttpStatus.OK);
        }
    }
}
