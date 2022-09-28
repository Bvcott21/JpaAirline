package com.edgarba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

//@Entity
public class Luggage {
   // @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "luggage_gen")
    //@SequenceGenerator(name = "luggage_gen", sequenceName="luggage_seq", allocationSize=1)
    private long luggageId;
    private int weight;

    public Luggage() {}

    public Luggage(int weight) {
        this.weight = weight;
    }

    public long getLuggageId() {
        return luggageId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Luggage [luggageId=" + luggageId + ", weight=" + weight + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (luggageId ^ (luggageId >>> 32));
        result = prime * result + weight;
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
        Luggage other = (Luggage) obj;
        if (luggageId != other.luggageId)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    };

    

    
}
