package com.edgarba.repository;

import java.util.List;

import com.edgarba.model.Airline;
import com.edgarba.repository.utility.CRUD;
import com.edgarba.exceptions.AirlineNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AirlineDao implements CRUD<Airline>{

    private EntityManagerFactory emf;

    public AirlineDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Airline airline) {
       EntityManager em = emf.createEntityManager();
        
       em.getTransaction().begin();
       em.persist(airline);
       em.getTransaction().commit();

       em.close();
    }

    @Override
    public List<Airline> findAll() {
        EntityManager em = emf.createEntityManager();
        
        List<Airline> airlinesFound = em.createQuery("SELECT a FROM Airline a", Airline.class)
            .getResultList();

        em.close();

        return airlinesFound;
    }

    @Override
    public Airline findById(long id) throws AirlineNotFoundException {
        EntityManager em = emf.createEntityManager();
        
        Airline airlineFound = em.find(Airline.class, id);
        
        em.close();

        if(airlineFound != null) {
            return airlineFound;
        } else {
            throw new AirlineNotFoundException("Airline not found, please try again");
        }
    }

    @Override
    public Airline update(Airline airline) throws AirlineNotFoundException {
        EntityManager em = emf.createEntityManager();
        
        Airline airlineFound = em.find(Airline.class, airline.getAirlineId());
        
        if(airlineFound != null) {
            em.getTransaction().begin();
            airline = em.merge(airline);
            em.getTransaction().commit();

            em.close();
            return airline;
        } else {
            em.close();
            throw new AirlineNotFoundException("The airline you are trying to update doesn't exist, perhaps you want to create a new one?");
        }
    }

    @Override
    public void deleteById(long id) throws AirlineNotFoundException {
        EntityManager em = emf.createEntityManager();
        Airline airlineFound = em.find(Airline.class, id);
        if(airlineFound != null) {
            em.getTransaction().begin();
            em.remove(airlineFound);
            em.getTransaction().commit();

            em.close();
        } else {

            em.close();
            throw new AirlineNotFoundException("Airline not found.");
        }
    }
    
}
