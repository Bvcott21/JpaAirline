package com.edgarba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airplane_gen")
    @SequenceGenerator(name = "airplane_gen", sequenceName = "airplane_seq", allocationSize = 1)
    private long airplaneId;
    @ManyToOne
    private Airline airline;
    private String airplaneModel;
    private int capacity;

    public Airplane() {}

    public Airplane(String airplaneModel, int capacity) {
        this.airplaneModel = airplaneModel;
        this.capacity = capacity;
    }
    public long getAirplaneId() {
        return airplaneId;
    }
    
    public Airline getAirline() {
        return airline;
    }
    public void setAirline(Airline airline) {
        this.airline = airline;
    }
    public String getAirplaneModel() {
        return airplaneModel;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane [airline=" + airline + ", airplaneId=" + airplaneId + ", airplaneModel=" + airplaneModel
                + ", capacity=" + capacity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airline == null) ? 0 : airline.hashCode());
        result = prime * result + (int) (airplaneId ^ (airplaneId >>> 32));
        result = prime * result + ((airplaneModel == null) ? 0 : airplaneModel.hashCode());
        result = prime * result + capacity;
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
        Airplane other = (Airplane) obj;
        if (airline == null) {
            if (other.airline != null)
                return false;
        } else if (!airline.equals(other.airline))
            return false;
        if (airplaneId != other.airplaneId)
            return false;
        if (airplaneModel == null) {
            if (other.airplaneModel != null)
                return false;
        } else if (!airplaneModel.equals(other.airplaneModel))
            return false;
        if (capacity != other.capacity)
            return false;
        return true;
    }
   
    
    
}
