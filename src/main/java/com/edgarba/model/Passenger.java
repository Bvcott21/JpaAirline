package com.edgarba.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_gen")
    @SequenceGenerator(name = "passenger_gen", sequenceName = "passenger_seq", allocationSize=1)
    private long passengerId;
    private String firstname, lastName;
    private int age;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Document> documents = new ArrayList<>();

    public Passenger() {}

    public Passenger(String firstname, String lastName) {
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        documents.add(document);
        this.setAge(LocalDate.now().getYear() - document.getDateOfBirth().getYear());
    }

    @Override
    public String toString() {
        return "Passenger [age=" + age + ", documents=" + documents + ", firstname=" + firstname + ", lastName="
                + lastName + ", passengerId=" + passengerId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((documents == null) ? 0 : documents.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + (int) (passengerId ^ (passengerId >>> 32));
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
        Passenger other = (Passenger) obj;
        if (age != other.age)
            return false;
        if (documents == null) {
            if (other.documents != null)
                return false;
        } else if (!documents.equals(other.documents))
            return false;
        if (firstname == null) {
            if (other.firstname != null)
                return false;
        } else if (!firstname.equals(other.firstname))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (passengerId != other.passengerId)
            return false;
        return true;
    }

    

}
