package com.edgarba.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.edgarba.exceptions.AirlineNotFoundException;
import com.edgarba.model.Address;
import com.edgarba.model.Airline;
import com.edgarba.model.ContactNumber;
import com.edgarba.model.Email;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AirlineDaoTest {
    private AirlineDao airlineDao;
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("JpaAirline");
        airlineDao = new AirlineDao(emf);
    }

    @AfterEach
    void closeUp() {
        emf.close();
    }

    @Test
    void testCreate_persistsANewAirline() {
        Airline airline = new Airline("Iberia");

        airlineDao.create(airline);

        assertEquals(airline.getName(), airlineDao.findById(1L).get().getName());
    }

    @Test
    void testDeleteById_deletesAnAirlineIfItExists() {
        Airline airline = new Airline("Iberia");
        airlineDao.create(airline);

        assertDoesNotThrow(() -> airlineDao.deleteById(1));
    }

    @Test
    void testDeleteById_throwsAirlineNotFoundException_whenAirlineDoesntExist() {
        assertThrows(AirlineNotFoundException.class, 
            () -> airlineDao.deleteById(100L));
    }

    @Test
    void testFindAll_returnsListOfThreeAirlines_whenThreeAirlinesPresent() {
        Airline airline1 = new Airline("Iberia");
        Airline airline2 = new Airline("British Airways");
        Airline airline3 = new Airline("Conviasa");

        airlineDao.create(airline1);
        airlineDao.create(airline2);
        airlineDao.create(airline3);

        List<Airline> airlinesFound = airlineDao.findAll();
        assertEquals(3, airlinesFound.size());
    }

    @Test
    void testFindById() {

    }

    @Test
    void testUpdate() {
        Airline airline1 = new Airline("Iberia");
        airlineDao.create(airline1);

        airline1.setName("British Airways");
        airlineDao.update(airline1);

        assertEquals("British Airways", airlineDao.findById(1L).get().getName());
    }
}
