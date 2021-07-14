/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrolsystem_question3;

/**
 *
 * @author Donovan van Heerden
 * Flight Class to store flight information
 */
public class Flight {
    public String flightName;
    public FlightPriority priority;
    
    /**
     * Creates an instance of the Flight Class
     * @param name the name of the flight
     * @param priority the priority given to the flight
     */
    public Flight(String name, FlightPriority priority) {
        this.flightName = name;
        this.priority = priority;
    }
}
