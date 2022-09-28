package com.edgarba.repository;

import java.util.List;
import java.util.Optional;

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
        Optional<Airline> airlineFound = Optional.ofNullable(em.find(Airline.class, id));
        em.close();
        return airlineFound;
    }

    @Override
    public Airline update(Airline airline) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        airline = em.merge(airline);
        em.getTransaction();

        em.close();

        return airline;
    }

    @Override
    public void deleteById(long id) throws AirlineNotFoundException {
        EntityManager em = emf.createEntityManager();
        Airline airlineFound = em.find(Airline.class, id);
        if(airlineFound != null) {
            em.getTransaction().begin();
            em.remove(airlineFound);
            em.getTransaction().commit();
        } else {
            throw new AirlineNotFoundException("Airline not found.");
        }
        
        em.close();
        
    }
    
}
