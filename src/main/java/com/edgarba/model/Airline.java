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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;


@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airline_gen")
    @SequenceGenerator(name = "airline_gen", sequenceName = "airline_seq", allocationSize = 1)
    private long airlineId;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Email> emails = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ContactNumber> contactNumbers = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Airplane> airplanes = new ArrayList<>();

    public Airline() {}
    
    public Airline(String name) {
        this.name = name;
    }

    public long getAirlineId() {
        return airlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void addEmail(Email mail) {
        emails.add(mail);
    }

    public List<ContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void addContactNumber(ContactNumber number) {
        contactNumbers.add(number);
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void addAirplane(Airplane airplane) {
        airplanes.add(airplane);
        airplane.setAirline(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
        result = prime * result + (int) (airlineId ^ (airlineId >>> 32));
        result = prime * result + ((airplanes == null) ? 0 : airplanes.hashCode());
        result = prime * result + ((contactNumbers == null) ? 0 : contactNumbers.hashCode());
        result = prime * result + ((emails == null) ? 0 : emails.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Airline other = (Airline) obj;
        if (addresses == null) {
            if (other.addresses != null)
                return false;
        } else if (!addresses.equals(other.addresses))
            return false;
        if (airlineId != other.airlineId)
            return false;
        if (airplanes == null) {
            if (other.airplanes != null)
                return false;
        } else if (!airplanes.equals(other.airplanes))
            return false;
        if (contactNumbers == null) {
            if (other.contactNumbers != null)
                return false;
        } else if (!contactNumbers.equals(other.contactNumbers))
            return false;
        if (emails == null) {
            if (other.emails != null)
                return false;
        } else if (!emails.equals(other.emails))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Airline [addresses=" + addresses + ", airlineId=" + airlineId + ", airplanes=" + airplanes
                + ", contactNumbers=" + contactNumbers + ", emails=" + emails + ", name=" + name + "]";
    }

    



   

   
    
    
}
