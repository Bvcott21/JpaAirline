package com.edgarba.repository;

import java.util.List;

import com.edgarba.exceptions.AirplaneNotFoundException;
import com.edgarba.model.Airplane;
import com.edgarba.repository.utility.CRUD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AirplaneDao implements CRUD<Airplane> {
    private EntityManagerFactory emf;
    
    public AirplaneDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Airplane airplane) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(airplane);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public List<Airplane> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Airplane> airplanesFound = em.createQuery("SELECT a FROM Airplane a", Airplane.class).getResultList();
        em.close();
        return airplanesFound;
    }

    @Override
    public Airplane findById(long id) throws AirplaneNotFoundException{
        EntityManager em = emf.createEntityManager();

        Airplane airplaneFound = em.find(Airplane.class, id);

        em.close();

        if(airplaneFound != null) {
            return airplaneFound;
        } else {
            throw new AirplaneNotFoundException("Airplane not found, please try again");
        }

        
    }

    @Override
    public Airplane update(Airplane airplane) throws AirplaneNotFoundException {
        EntityManager em = emf.createEntityManager();

        Airplane airplaneFound = em.find(Airplane.class, airplane.getAirplaneId());

        if(airplaneFound != null) {
            em.getTransaction().begin();
            airplane = em.merge(airplane);
            em.getTransaction().commit();

            em.close();
            return airplane;
        } else {
            throw new AirplaneNotFoundException("The airplane you are trying to update doesn't exist, perhaps you want to create a new one?");
        }

        
    }

    @Override
    public void deleteById(long id) throws AirplaneNotFoundException {
        EntityManager em = emf.createEntityManager();
        Airplane airplaneFound = em.find(Airplane.class, id);
        if(airplaneFound != null) {
            em.getTransaction().begin();
            em.remove(airplaneFound);
            em.getTransaction().commit();
        } else {
            throw new AirplaneNotFoundException("Couldn't delete. Airplane not found.");
        }
        
    }
    
}
