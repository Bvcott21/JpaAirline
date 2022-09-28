package com.edgarba.service;

import java.util.List;
import java.util.Optional;

import com.edgarba.exceptions.AirlineNotFoundException;
import com.edgarba.model.Airline;
import com.edgarba.repository.AirlineDao;

import jakarta.persistence.EntityManagerFactory;

public class AirlineService {
    private AirlineDao airlineDao;

    public AirlineService(EntityManagerFactory emf) {
        this.airlineDao = new AirlineDao(emf);
    }

    public void create(Airline airline) {
        airlineDao.create(airline);
    }

    public List<Airline> findAll() {
        return airlineDao.findAll();
    }

    public Optional<Airline> findById(long id) {
        return airlineDao.findById(id);
    }

    public Airline update(Airline airline) {
        return airlineDao.update(airline);
    }

    public void delete(Airline airline) {
        try {
            airlineDao.deleteById(airline.getAirlineId());
        } catch (AirlineNotFoundException e) {
            e.printStackTrace();
        }
    }
}
