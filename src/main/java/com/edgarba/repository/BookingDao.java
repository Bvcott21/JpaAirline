package com.edgarba.repository;

import java.util.List;

import com.edgarba.exceptions.BookingNotFoundException;
import com.edgarba.model.Booking;
import com.edgarba.repository.utility.CRUD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class BookingDao implements CRUD<Booking> {
    private EntityManagerFactory emf;

    public BookingDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Booking booking) {
       EntityManager em = emf.createEntityManager();

       em.getTransaction().begin();
       em.persist(booking);
       em.getTransaction().commit();

       em.close();
    }

    @Override
    public List<Booking> findAll() {
        EntityManager em = emf.createEntityManager();

        List<Booking> bookingsFound = em.createQuery("SELECT b FROM Booking b", Booking.class)
            .getResultList();
        
        em.close();

        return bookingsFound;
    }

    @Override
    public Booking findById(long id) throws BookingNotFoundException {
        EntityManager em = emf.createEntityManager();

        Booking bookingFound = em.find(Booking.class, id);

        em.close();

        if(bookingFound != null) {
            return bookingFound;
        } else {
            throw new BookingNotFoundException("Booking not found, please try again");
        }
    }


    @Override
    public Booking update(Booking booking) throws BookingNotFoundException {
        EntityManager em = emf.createEntityManager();

        Booking bookingFound = em.find(Booking.class, booking.getBookingId());

        if(bookingFound != null) {
            em.getTransaction().begin();
            booking = em.merge(booking);
            em.getTransaction().commit();

            em.close();
            return booking;
        } else {
            em.close();
            throw new BookingNotFoundException("The booking you are trying to update doesn't exist.");
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        EntityManager em = emf.createEntityManager();

        Booking bookingFound = em.find(Booking.class, id);

        if(bookingFound != null) {
            em.getTransaction().begin();
            em.remove(bookingFound);
            em.getTransaction().commit();

            em.close();
        } else {
            em.close();
            throw new BookingNotFoundException("Booking not found, please try again");
        }
        
    }
    
}
