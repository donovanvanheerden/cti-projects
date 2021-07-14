/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrolsystem_question3;

/**
 *
 * @author Donovan van Heerden
 * Flight Priority Class
 */
public class FlightPriority {
    private String key;
    private int value;
    
    /**
     * Creates a new instance of the Flight Priority Class
     * @param key the string key for the Flight Priority eg. "High"
     * @param value  the int value for the key eg. 1
     */
    public FlightPriority(String key, int value) {
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
