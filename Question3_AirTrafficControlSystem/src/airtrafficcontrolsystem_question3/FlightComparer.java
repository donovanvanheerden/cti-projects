/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrolsystem_question3;

import java.util.Comparator;

/**
 *
 * @author Donovan van Heerden
 * FlightComparer used to compare the Flight Priorities
 */
public class FlightComparer implements Comparator<Flight> {
    @Override
    public int compare(Flight x, Flight y) {

        if (x.priority.getValue() < y.priority.getValue())
        {
            return -1;
        }
        if (x.priority.getValue() > y.priority.getValue())
        {
            return 1;
        }
        
        return 0;
    }
}
