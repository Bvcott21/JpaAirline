package com.edgarba.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.edgarba.exceptions.AirlineNotFoundException;
import com.edgarba.model.Address;
import com.edgarba.model.Airline;
import com.edgarba.model.Airplane;
import com.edgarba.model.ContactNumber;
import com.edgarba.model.Email;

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
    void test_findAll_returnsEmptyList_whenNoAirlinesPersisted() {
        List<Airline> airlinesFound = airlineDao.findAll();
        assertTrue(airlinesFound.isEmpty());
    }

    @Test
    void test_Create_persistsANewAirline() {
        Airline airline = new Airline("Iberia");
        airlineDao.create(airline);
        assertEquals(airline.getName(), airlineDao.findAll().get(0).getName());
    }

    @Test
    void test_create_persistsAirlineWithAllDetails() {
        ContactNumber number = new ContactNumber("Sales", "12345678"); 
        Address address = new Address("postCode", "city", "state", "country");
        Airplane airplane = new Airplane("airplaneModel", 123);
        Email email = new Email("name", "email");

        Airline airline = new Airline("Airline");

        airline.addContactNumber(number);
        airline.addAddress(address);
        airline.addAirplane(airplane);
        airline.addEmail(email);

        airlineDao.create(airline);

        Airline airlineFound = airlineDao.findAll().get(0);

        assertEquals(number, airlineFound.getContactNumbers().get(0));
        assertEquals(address, airlineFound.getAddresses().get(0));
        assertEquals(airplane.getAirplaneId(), airlineFound.getAirplanes().get(0).getAirplaneId());
        assertEquals(email, airlineFound.getEmails().get(0));
    }

    @Test
    void test_findAll_returnsListWithThreeElements_whenThreeAirlinesPersisted() {
        Airline airline1 = new Airline("Iberia");
        Airline airline2 = new Airline("Iberia");
        Airline airline3 = new Airline("Iberia");

        airlineDao.create(airline1);
        airlineDao.create(airline2);
        airlineDao.create(airline3);

        assertEquals(3, airlineDao.findAll().size());
    }
    
    @Test
    void test_findById_throwsAirplaneNotFoundException_whenAirplaneNotFound() {
        assertThrows(AirlineNotFoundException.class, () -> {
            airlineDao.findById(0);
        });
    }

    @Test
    void test_findById_doesNotThrowAnException_whenAirlineFound() {
        Airline airline1 = new Airline("Iberia");
        airlineDao.create(airline1);
        assertDoesNotThrow(() -> {
            airlineDao.findById(1);
        });
    }

    @Test
    void test_findById_findsAirlineWithRightDetails() {
        assertDoesNotThrow(() -> {
            Airline airline = new Airline("Iberia");
            airlineDao.create(airline);
            Airline airlineFound = airlineDao.findById(1);
            assertEquals(airline.getName(), airlineFound.getName());
        });
    }

    @Test
    void test_deleteById_throwsAirlineNotFoundException_whenAirlineNotFound() {
        assertThrows(AirlineNotFoundException.class, () -> {
            airlineDao.deleteById(1);
        });
    }

    @Test
    void test_deleteById_successfullyDeletesAPersistedAirline() {
        assertDoesNotThrow(() -> {
            Airline airline = new Airline("Iberia");
            airlineDao.create(airline);
            airlineDao.deleteById(1);
        });
    }

    @Test
    void test_update_throwsAirlineNotFoundException_whenAirlineNotFound() {
        assertThrows(AirlineNotFoundException.class, () -> {
            Airline airline = new Airline("Iberia");
            airlineDao.update(airline);
        });
    }

    @Test
    void test_update_successfullyUpdatesAnExistingAirline() {
        assertDoesNotThrow(() -> {
            Airline airline = new Airline("Iberia");
            airlineDao.create(airline);

            airline.setName("abc");

            airlineDao.update(airline);

            Airline airlineUpdated = airlineDao.findById(1);
            assertEquals(airline.getName(), airlineUpdated.getName());
        });
    }



}
