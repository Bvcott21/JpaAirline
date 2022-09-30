package com.edgarba.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.edgarba.exceptions.BookingNotFoundException;
import com.edgarba.model.Booking;
import com.edgarba.model.Flight;
import com.edgarba.model.Passenger;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BookingDaoTest {
    BookingDao bookingDao;
    EntityManagerFactory emf;

    @Mock
    Flight flight;

    @Mock
    Passenger passenger;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("JpaAirline");
        bookingDao = new BookingDao(emf);
    }

    @AfterEach
    void closeUp() {
        emf.close();
    }

    @Test
    void testFindAll_returnsEmptyList_whenNoBookingsPersisted() {
        List<Booking> bookingsFound = bookingDao.findAll();
        assertTrue(bookingsFound.isEmpty());
    }

    @Test
    void testCreate_createsABooking() {
        Booking booking = new Booking(flight);

        bookingDao.create(booking);
        assertEquals(booking.getBookingId(), bookingDao.findAll().get(0).getBookingId());
    }

    @Test
    void testFindAll_returnsListWithThreeBookings_whenThreeBookingsPersisted() {
        Booking booking1 = new Booking(flight);
        Booking booking2 = new Booking(flight);
        Booking booking3 = new Booking(flight);

        bookingDao.create(booking1);
        bookingDao.create(booking2);
        bookingDao.create(booking3);

        assertEquals(3, bookingDao.findAll().size());
    }

    @Test
    void testFindById_throwsBookingNotFoundException_whenBookingNotFound() {
        assertThrows(BookingNotFoundException.class, () -> {
            bookingDao.findById(1);
        });
    }

    @Test
    void test_findById_doesNotThrowExceptionWhenBookingFound() {
        Booking booking1 = new Booking(flight);

        bookingDao.create(booking1);
        
        assertDoesNotThrow(() -> {
            bookingDao.findById(1);
        });
    }

    @Test
    void test_deleteById_throwsBookingNotFoundException_whenBookingNotFound() {
        assertThrows(BookingNotFoundException.class, () -> {
            bookingDao.deleteById(999);
        });
    }

    @Test
    void test_deleteById_doesNotThrowExceptionWhenBookingFound() {
        Booking booking1 = new Booking(flight);

        bookingDao.create(booking1);
        
        assertDoesNotThrow(() -> {
            bookingDao.deleteById(1);
        });
    }

    @Test
    void test_update_throwsBookingNotFound_ifBookingDoesntExist() {
        Booking booking1 = new Booking(flight);
        assertThrows(BookingNotFoundException.class, () -> {
            bookingDao.update(booking1);
        });
    }

    @Test
    void test_update_successfullyUpdatesPersistedBooking() {
        Booking booking1 = new Booking(flight);
        bookingDao.create(booking1);
        booking1.addPassenger(passenger);
        assertDoesNotThrow(() -> {
            bookingDao.update(booking1);
        });
    }
}
