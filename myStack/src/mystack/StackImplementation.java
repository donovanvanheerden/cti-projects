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
public class StackImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        myStack FruitBasket = new myStack();
        
        FruitBasket.push(new Fruit("Apple", 10));
        FruitBasket.push(new Fruit("Banana", 12));
        FruitBasket.push(new Fruit("Pineapple", 2));
        FruitBasket.push(new Fruit("Grape", 7));
        FruitBasket.push(new Fruit("Pear", 5));
        
        System.out.println(FruitBasket.toString() + "\n");
        
        while(!FruitBasket.isEmpty()) {
            Fruit value = FruitBasket.pop();
            System.out.println(value.getTotal() + " " + value.getName() + "s are ready for delivery.");
        }
        
        System.out.println("\nIs the fruit basket empty? " + FruitBasket.isEmpty());
        //System.out.println(FruitBasket.pop());
        
    }
    
}
