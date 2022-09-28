package com.fdmgroup.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.edgarba.model.Airline;

public class AirlineTest {
    
    @Test 
    void test_passIfTwoAirlinesWithSameAttributesExist() {
        Airline airline1 = new Airline("Iberia");
        Airline airline2 = new Airline("Iberia");
        assertEquals(airline1, airline2);
    }
}
