package com.edgarba.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Luggage {
   
    private int weight;

    public Luggage() {}

    public Luggage(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        if (weight != other.weight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Luggage [weight=" + weight + "]";
    }

   

    

    
}
