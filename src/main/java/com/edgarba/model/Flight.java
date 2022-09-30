package com.edgarba.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_gen")
    @SequenceGenerator(name = "flight_gen", sequenceName="flight_seq", allocationSize=1)
    private long flightId;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Airplane airplane;
    private String flightCode;
    private String origin, destination;
    private LocalDateTime departureDateTime, arrivalDateTime;
    private int availableSeats;
    private double ticketPrice;
    @OneToMany
    private List<Booking> bookings = new ArrayList<Booking>();

    public Flight() {}

    public Flight(Airplane airplane, String flightCode, String origin, String destination,
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int availableSeats, double ticketPrice) {
        this.airplane = airplane;
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.availableSeats = airplane.getCapacity();
        this.ticketPrice = ticketPrice;
    }

    public long getFlightId() {
        return flightId;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        if(bookings.contains(booking)) {
            bookings.remove(booking);
        }
    }
    

    @Override
    public String toString() {
        return "Flight [airplane=" + airplane + ", arrivalDateTime=" + arrivalDateTime + ", availableSeats="
                + availableSeats + ", departureDateTime=" + departureDateTime + ", destination=" + destination
                + ", flightCode=" + flightCode + ", flightId=" + flightId + ", origin=" + origin + ", ticketPrice="
                + ticketPrice + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airplane == null) ? 0 : airplane.hashCode());
        result = prime * result + ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
        result = prime * result + availableSeats;
        result = prime * result + ((departureDateTime == null) ? 0 : departureDateTime.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((flightCode == null) ? 0 : flightCode.hashCode());
        result = prime * result + (int) (flightId ^ (flightId >>> 32));
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        long temp;
        temp = Double.doubleToLongBits(ticketPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Flight other = (Flight) obj;
        if (airplane == null) {
            if (other.airplane != null)
                return false;
        } else if (!airplane.equals(other.airplane))
            return false;
        if (arrivalDateTime == null) {
            if (other.arrivalDateTime != null)
                return false;
        } else if (!arrivalDateTime.equals(other.arrivalDateTime))
            return false;
        if (availableSeats != other.availableSeats)
            return false;
        if (departureDateTime == null) {
            if (other.departureDateTime != null)
                return false;
        } else if (!departureDateTime.equals(other.departureDateTime))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (flightCode == null) {
            if (other.flightCode != null)
                return false;
        } else if (!flightCode.equals(other.flightCode))
            return false;
        if (flightId != other.flightId)
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (Double.doubleToLongBits(ticketPrice) != Double.doubleToLongBits(other.ticketPrice))
            return false;
        return true;
    }    
    

}
