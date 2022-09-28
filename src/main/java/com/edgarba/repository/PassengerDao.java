package com.edgarba.repository;

import java.util.List;

import com.edgarba.exceptions.PassengerNotFoundException;
import com.edgarba.model.Passenger;
import com.edgarba.repository.utility.CRUD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PassengerDao implements CRUD<Passenger>{

    private EntityManagerFactory emf;

    public PassengerDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Passenger passenger) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public List<Passenger> findAll() {
        EntityManager em = emf.createEntityManager();

        List<Passenger> passengersFound = em.createQuery("SELECT p FROM Passenger p", Passenger.class)
            .getResultList();
        
        em.close();

        return passengersFound;
    }

    @Override
    public Passenger findById(long id) throws PassengerNotFoundException {
        EntityManager em = emf.createEntityManager();

        Passenger passengerFound = em.find(Passenger.class, id);

        em.close();

        if(passengerFound != null) {
            return passengerFound;
        } else {
            throw new PassengerNotFoundException("Passenger not found, please try again,");
        }
    }

    @Override
    public Passenger update(Passenger passenger) throws PassengerNotFoundException {
        EntityManager em = emf.createEntityManager();

        Passenger passengerFound = em.find(Passenger.class, passenger.getPassengerId());

        if(passengerFound != null) {
            em.getTransaction().begin();
            passenger = em.merge(passenger);
            em.getTransaction().commit();

            em.close();
            return passenger;
        } else {
            em.close();
            throw new PassengerNotFoundException("The passenger you are trying to update doesn't exist, you might want to create a new one instead");
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        EntityManager em = emf.createEntityManager();

        Passenger passengerFound = em.find(Passenger.class, id);

        if(passengerFound != null) {
            em.getTransaction().begin();
            em.remove(passengerFound);
            em.getTransaction().commit();

            em.close();
        } else {
            em.close();
            throw new PassengerNotFoundException("The passenger you are trying to delete doesn't exist");
        }
    }

}
