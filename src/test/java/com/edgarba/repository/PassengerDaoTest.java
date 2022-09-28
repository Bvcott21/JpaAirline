package com.edgarba.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.edgarba.exceptions.PassengerNotFoundException;
import com.edgarba.model.Country;
import com.edgarba.model.Document;
import com.edgarba.model.DocumentType;
import com.edgarba.model.Passenger;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PassengerDaoTest {
    PassengerDao passengerDao;
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("JpaAirline");
        passengerDao = new PassengerDao(emf);
    }

    @AfterEach
    void closeUp() {
        emf.close();
    }

    @Test
    void test_findAll_returnsEmptyList_whenNoPassengersPersisted() {
        List<Passenger> passengersFound = passengerDao.findAll();
        assertTrue(passengersFound.isEmpty());
    }

    @Test
    void test_create_createsAPassenger() {
        Passenger passenger = new Passenger("Edgar", "Bucott");
        passengerDao.create(passenger);
        assertEquals(passenger.getFirstname(), passengerDao.findAll().get(0).getFirstname());
    }

    @Test
    void test_create_createsPassengerWithDocuments() {
        Document document = new Document(DocumentType.PASSPORT, "ABC123", LocalDate.of(1995, Month.JULY, 21), Country.SPAIN);

        Passenger passenger = new Passenger("Edgar", "Bucott");
        passenger.addDocument(document);

        passengerDao.create(passenger);
        assertEquals(passenger.getFirstname(), passengerDao.findAll().get(0).getFirstname());
    }

    @Test
    void test_findAll_returnsListWithThreePassengers_whenThreePassengersPersisted() {
        Passenger passenger1 = new Passenger("p1name", "p1lname");
        Passenger passenger2 = new Passenger("p2name", "p2lname");
        Passenger passenger3 = new Passenger("p3name", "p3lname");

        passengerDao.create(passenger1);
        passengerDao.create(passenger2);
        passengerDao.create(passenger3);

        assertEquals(3, passengerDao.findAll().size());
    }

    @Test
    void test_findById_throwsPassengerNotFoundException_whenPassengerNotFound() {
        assertThrows(PassengerNotFoundException.class, () -> {
            passengerDao.findById(1);
        });
    }

    @Test
    void test_findByIdDoesNotThrowAnException_whenPassengerFound() {
        Passenger passenger = new Passenger("asd", "asd");
        passengerDao.create(passenger);
        assertDoesNotThrow(() -> {
            passengerDao.findById(1);
        });
    }

    @Test
    void test_findById_findsPassengerWithRightDetails() {
        assertDoesNotThrow(() -> {
            Passenger passenger = new Passenger("asd", "asd");

            passengerDao.create(passenger);

            Passenger passengerFound = passengerDao.findById(1);
            assertEquals(passenger.getFirstname(), passengerFound.getFirstname());
        });
    }

    @Test
    void test_deleteById_throwsPassengerNotFoundException_whenPassengerNotFound() {
        assertThrows(PassengerNotFoundException.class, () -> {
            passengerDao.deleteById(1);
        });
    }

    @Test
    void test_deleteById_succesfullydeletesAPersistedPassenger() {
        assertDoesNotThrow(() -> {
            Passenger passenger = new Passenger("asd", "asd");

            passengerDao.create(passenger);

            passengerDao.deleteById(1);
        });
    }

    @Test
    void test_update_throwsPassengerNotFoundException_whenPassengerNotFound() {
        assertThrows(PassengerNotFoundException.class, () -> {
            Passenger passenger = new Passenger("asd", "asd");
            passengerDao.update(passenger);
        });
    }

    @Test
    void test_update_successfullyUpdatesPersistedPassenger() {
        assertDoesNotThrow(() -> {
            Passenger passenger = new Passenger("asd", "asd");
            passengerDao.create(passenger);
            passenger.setFirstname("xyz");
            passengerDao.update(passenger);

            Passenger passengerUpdated = passengerDao.findById(1);
            assertEquals(passenger.getFirstname(), passengerUpdated.getFirstname());
        });
    }

    
}
