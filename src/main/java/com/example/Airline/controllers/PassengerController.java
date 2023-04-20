package com.example.Airline.controllers;

import com.example.Airline.models.Flight;
import com.example.Airline.models.Passenger;
import com.example.Airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Long id){
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        if (passenger.isPresent()){
            return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger){
        Passenger newPassenger = passengerService.addNewPassenger(passenger);
        return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Passenger> addPassengerToFlight(
            @PathVariable Long id,
            @RequestParam Long flightId
    ){
        Passenger passenger = passengerService.addPassengerToFlight(id, flightId);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

}
