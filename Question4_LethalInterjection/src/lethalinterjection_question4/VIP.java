/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lethalinterjection_question4;

/**
 *
 * @author Donovan van Heerden
 * VIP Class to store VIP information
 */
public class VIP {
    private String first_name;
    private String last_name;
    private int age;
    
    /**
     * Creates a new instance of the VIP class
     * @param first_name VIP's first name
     * @param last_name VIP's last name
     * @param age  VIP's age
     */
    public VIP (String first_name, String last_name, int age){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }
    
    /**
     * @return VIP's first name + last name
     */
    public String getName() {
        return this.first_name + " " + this.last_name;
    }
    
    /**
     * @return VIP's age
     */
    public int getAge() {
        return this.age;
    }
}
