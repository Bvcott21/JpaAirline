package com.edgarba;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("JpaAirline");
        System.out.println("Connected to the database");
        emf.close();
    }
}
