package com.edgarba.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq", allocationSize = 1)
    private long bookingId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Flight flight;

    
    @OneToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
        fetch = FetchType.EAGER)
    private List<Passenger> passengers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Luggage> luggage = new ArrayList<>();

    public Booking() {}
    
    public Booking(Flight flight) {
        this.flight = flight;
    }

    public long getBookingId() {
        return bookingId;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Luggage> getLuggage() {
        return luggage;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void addLuggage(Luggage individualLuggage) {
        luggage.add(individualLuggage);
    }

    public void removePassenger(Passenger passenger) {
        if(passengers.contains(passenger)) {
            passengers.remove(passenger);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (bookingId ^ (bookingId >>> 32));
        result = prime * result + ((flight == null) ? 0 : flight.hashCode());
        result = prime * result + ((luggage == null) ? 0 : luggage.hashCode());
        result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (bookingId != other.bookingId)
            return false;
        if (flight == null) {
            if (other.flight != null)
                return false;
        } else if (!flight.equals(other.flight))
            return false;
        if (luggage == null) {
            if (other.luggage != null)
                return false;
        } else if (!luggage.equals(other.luggage))
            return false;
        if (passengers == null) {
            if (other.passengers != null)
                return false;
        } else if (!passengers.equals(other.passengers))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", flight=" + flight + ", luggage=" + luggage + ", passengers="
                + passengers + "]";
    }
    
    
}
