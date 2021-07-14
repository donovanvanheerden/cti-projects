/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localautomechanic_question1;

/**
 *
 * @author Donovan van Heerden
 * 
 * CarService Object, stores the car registration number and client name.
 */
public class CarService {
    String registration_number;
    String client_name;
    
    /**
     * Creates a new Instance of the CarService object
     * 
     * @param reg_number car registration number
     * @param name client's name
     */
    public CarService(String reg_number, String name) {
        this.registration_number = reg_number;
        this.client_name = name;
    }
    
    /**
     * 
     * @return car registration number
     */
    public String getRegNumber() {
        return this.registration_number;
    }
    
    /**
     * 
     * @return client's name
     */
    public String getClientName() {
        return this.client_name;
    }
}
