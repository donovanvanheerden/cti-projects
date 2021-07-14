/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrolsystem_question3;

/**
 * @author Donovan van Heerden
 * FlightType Class for storing Flight Type information
 */
public class FlightType {
    private String key;
    private int value;
    
    /**
     * Creates a new instance of the FlightType class.
     * @param key the string name of the flight type eg. "Take-off"
     * @param value  the int value of the key eg. 1
     */
    public FlightType(String key, int value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }
    
    /**
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * @return the key as the toString() default
     */
    @Override
    public String toString() {
        return this.key;
    }
}
