package com.edgarba.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.edgarba.exceptions.AirplaneNotFoundException;
import com.edgarba.model.Airplane;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AirplaneDaoTest {
    private AirplaneDao airplaneDao;
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("JpaAirline");
        airplaneDao = new AirplaneDao(emf);
    }

    @AfterEach
    void closeUp() {
        emf.close();
    }


    @Test
    void testFindAll_returnsEmptyList_whenNoAirplanesPersisted() {
        List<Airplane> airplanesFound = airplaneDao.findAll();
        assertTrue(airplanesFound.isEmpty());
    }

    @Test
    void testCreate_createsAnAirplane() {
        Airplane airplane = new Airplane("Boeing 737", 188);
        airplaneDao.create(airplane);
        assertEquals(airplane, airplaneDao.findAll().get(0));
    }

    @Test
    void testFindAll_returnsListWithThreeAirplanes_whenThreeAirplanesPersisted() {
        Airplane airplane1 = new Airplane("Boeing 737", 188);
        Airplane airplane2 = new Airplane("Boeing 747 Three-Class", 416);
        Airplane airplane3 = new Airplane("Boeing 747 Two-Class", 524);

        airplaneDao.create(airplane1);
        airplaneDao.create(airplane2);
        airplaneDao.create(airplane3);

        assertEquals(3, airplaneDao.findAll().size());
    }

    @Test
    void testFindById_throwsAirplaneNotFoundException_whenAirplaneNotFound() {
        assertThrows(AirplaneNotFoundException.class, 
        () -> {
            airplaneDao.findById(1);
        });
    }

    @Test
    void test_findById_doesNotThrowAnException_whenAirplaneFound() {
        Airplane airplane1 = new Airplane("Boeing 737", 188);

        airplaneDao.create(airplane1);

        assertDoesNotThrow(() -> {
            airplaneDao.findById(1);
        });
    }

    @Test
    void testFindById_findAnAirplane_withRightDetails() throws AirplaneNotFoundException {
        Airplane airplane1 = new Airplane("Boeing 737", 188);

        airplaneDao.create(airplane1);
        Airplane airplaneFound = airplaneDao.findById(1L);
        

        assertEquals(airplane1, airplaneFound);
    }

    @Test
    void testFindAll_returnsListWithThreeAirplanesWithTheRightDetails_whenThreeAirplanesPersisted() throws AirplaneNotFoundException {
        Airplane airplane1 = new Airplane("Boeing 737", 188);
        Airplane airplane2 = new Airplane("Boeing 747 Three-Class", 416);
        Airplane airplane3 = new Airplane("Boeing 747 Two-Class", 524);

        airplaneDao.create(airplane1);
        airplaneDao.create(airplane2);
        airplaneDao.create(airplane3);

        assertEquals(airplane1, airplaneDao.findById(1));
        assertEquals(airplane2, airplaneDao.findById(2));
        assertEquals(airplane3, airplaneDao.findById(3));
    }

    @Test
    void testDeleteById_throwsAirplaneNotFoundException_whenAirplaneNotFound() {
        assertThrows(AirplaneNotFoundException.class, () -> {
            airplaneDao.deleteById(1);
        });
    }

    @Test
    void test_deleteById_successfullyDeletesAirplane_whenExistent() {
        Airplane airplane1 = new Airplane("Boeing 737", 188);

        airplaneDao.create(airplane1);
        assertDoesNotThrow(() -> {
            airplaneDao.deleteById(1);
        });
    }

    @Test
    void test_update_throwsAirplaneNotFoundException_ifAirplaneDoesntExist() {
        Airplane airplane = new Airplane("test", 123);
        assertThrows(AirplaneNotFoundException.class, () -> {
            airplaneDao.update(airplane);
        });
    }

    @Test
    void test_update_successfullyUpdatePersistedAirplane() throws AirplaneNotFoundException {
        Airplane airplane = new Airplane("test", 123);
        airplaneDao.create(airplane);
        airplane.setCapacity(9999);
        assertDoesNotThrow(() -> {
            airplaneDao.update(airplane);
        });
        Airplane persistedPlane = airplaneDao.findById(1);
        assertEquals(airplane, persistedPlane);  
    }
}
