package com.edgarba.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactNumber {
    private String name;
    private String number;
    public ContactNumber() {
    }
    public ContactNumber(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public String toString() {
        return "ContactNumber [name=" + name + ", number=" + number + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
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
        ContactNumber other = (ContactNumber) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }
    
    
}
