package com.example.Airline.components;

import com.example.Airline.models.Flight;
import com.example.Airline.models.Passenger;
import com.example.Airline.repositories.FlightRepository;
import com.example.Airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){}


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Flight flight1 = new Flight(
                "London",
                200,
                LocalDate.of(2023,05,02),
                LocalTime.of(19,00));
        flightRepository.save(flight1);

        Flight flight2 = new Flight(
                "Paris",
                180,
                LocalDate.of(2023,01,01),
                LocalTime.of(12,00));
        flightRepository.save(flight2);

        Flight flight3 = new Flight(
                "Paris",
                1,
                LocalDate.of(2024,01,01),
                LocalTime.of(9,00));
        flightRepository.save(flight3);


        Passenger will = new Passenger("Will", 314159265L);
        passengerRepository.save(will);

        Passenger zsolt = new Passenger("Zsolt", 979323846L);
        passengerRepository.save(zsolt);

        Passenger anna = new Passenger("Anna", 2643383279L);
        passengerRepository.save(anna);

        will.addFlight(flight1);
        will.addFlight(flight2);
        zsolt.addFlight(flight2);
        anna.addFlight(flight2);
        anna.addFlight(flight3);

        passengerRepository.save(will);
        passengerRepository.save(zsolt);
        passengerRepository.save(anna);

    }
}
