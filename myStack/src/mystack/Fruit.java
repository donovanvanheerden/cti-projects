/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

/**
 *
 * @author DocMad
 */
public class Fruit {
    private String name;
    private int total;
    
    public Fruit(String name, int total) {
        this.name = name;
        this.total = total;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getTotal() {
        return this.total;
    }
}
